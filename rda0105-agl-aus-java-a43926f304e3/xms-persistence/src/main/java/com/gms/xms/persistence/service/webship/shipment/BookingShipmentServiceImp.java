package com.gms.xms.persistence.service.webship.shipment;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.model.webship.ship.BookingDataVo;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.*;
import com.gms.xms.persistence.dao.webship.addressbook.CustomerAddressBookDao;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.webship.CustomerAddressBookVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

public class BookingShipmentServiceImp implements IBookingShipmentService {
    private static final Log logger = LogFactory.getLog(BookingShipmentServiceImp.class);

    @Override
    public BookingDataVo bookingShipment(Map<String, String> context, BookingDataVo bookingDataVo) throws DaoException {
        SqlSessionClient sessionClient = new SqlSessionClient();

        ShipmentDao smDao = new ShipmentDao(sessionClient);
        PieceDao pDao = new PieceDao(sessionClient);
        AddressDao aDao = new AddressDao(sessionClient);
        ShipmentDetailDao smdDao = new ShipmentDetailDao(sessionClient);
        ShipmentProductDetailDao shipmentProductDetailDao = new ShipmentProductDetailDao(sessionClient);
        CustomerAddressBookDao customerAddressBookDao = new CustomerAddressBookDao(sessionClient);// dangdh
        try {
            sessionClient.startTransaction();
            AddressVo sAddress = bookingDataVo.getsAddress();
            AddressVo rAddress = bookingDataVo.getrAddress();
            ShipmentVo shipmentVo = bookingDataVo.getShipmentVo();
            List<PieceVo> pieces = bookingDataVo.getPieces();
            List<ShipmentDetailVo> shipmentDetails = bookingDataVo.getShipmentDetails();
            List<ShipmentProductDetailVo> shipmentProductDetailVos = bookingDataVo.getShipmentProductDetail();
            if (bookingDataVo.getShipmentInfoVo().getIsSaveRecipientAddressBook() == 1) {
                customerAddressBookDao.insertCustomerAddressBook(context, GetCustomerAddressBookVo(bookingDataVo, rAddress));
            }
            if (bookingDataVo.getShipmentInfoVo().getIsSaveSenderAddressBook() == 1) {
                customerAddressBookDao.insertCustomerAddressBook(context, GetCustomerAddressBookVo(bookingDataVo, sAddress));
            }
            // Put signal to the context to log insert address as receiver
            // address.
            context.put(Attributes.ADD_INFO_EXT_LOG_ACTION_TYPE, "Receiver Address"); //previous code
            //context.put(Attributes.ADD_INFO_EXT_LOG_ACTION_TYPE, rAddress.toString());
            aDao.insert(context, rAddress);
            // Put signal to the context to log insert address as sender
            // address.
            context.put(Attributes.ADD_INFO_EXT_LOG_ACTION_TYPE, "Sender Address");  //prevoius code
            //context.put(Attributes.ADD_INFO_EXT_LOG_ACTION_TYPE, sAddress.toString());
            aDao.insert(context, sAddress);
            shipmentVo.setSenderAddressId(sAddress.getAddressId());
            shipmentVo.setReceiverAddressId(rAddress.getAddressId());
            // Put signal to the context to log insert shipment as shipment
            // request.
            context.put(Attributes.ADD_INFO_EXT_LOG_ACTION_TYPE, "Shipment Request");  //prevoius code
            //context.put(Attributes.ADD_INFO_EXT_LOG_ACTION_TYPE, shipmentVo.toString());
            smDao.insertShipment(context, shipmentVo);
            for (ShipmentDetailVo shipmentDetail : shipmentDetails) {
                shipmentDetail.setShipmentId(shipmentVo.getShipmentId());
                // Put signal to the context to log insert shipment detail as
                // Shipment Detail.
                context.put(Attributes.ADD_INFO_EXT_LOG_ACTION_TYPE, "Shipment Detail"); //prevoius code
                //context.put(Attributes.ADD_INFO_EXT_LOG_ACTION_TYPE, shipmentDetail.toString());
                smdDao.insert(context, shipmentDetail);
            }
            if (shipmentProductDetailVos != null) {
                for (ShipmentProductDetailVo shipmentProductDetailVo : shipmentProductDetailVos) {
                    shipmentProductDetailVo.setShipmentId(shipmentVo.getShipmentId());
                    if (shipmentProductDetailVo.getNoOfCarton() != null) {

                    } else {
                        shipmentProductDetailVo.setNoOfCarton(0);
                    }
                    shipmentProductDetailDao.insert(context, shipmentProductDetailVo);
                }
            }
            Double totalWeight = 0D;
            for (PieceVo piece : pieces) {
                piece.setShipmentId(shipmentVo.getShipmentId());
                for (int i = 1; i <= piece.getQuantity(); i++) {
                    pDao.insert(context, piece);
                }
                totalWeight += piece.getWeight();
            }
            bookingDataVo.setShipmentVo(shipmentVo);
            bookingDataVo.setPieces(pieces);
            bookingDataVo.setrAddress(rAddress);
            bookingDataVo.setsAddress(sAddress);
            bookingDataVo.setShipmentDetails(shipmentDetails);
            // Commit transaction
            sessionClient.endTransaction();
        } catch (Exception e) {
            // Log database action error
            logger.error(e);

            // Roll back transaction
            sessionClient.rollback();
            throw e;
        }
        return bookingDataVo;
    }

    private CustomerAddressBookVo GetCustomerAddressBookVo(BookingDataVo bookingDataVo, AddressVo addressVo) {
        CustomerAddressBookVo customerAddressBookVo = new CustomerAddressBookVo();
        customerAddressBookVo.setCustomerCode(bookingDataVo.getShipmentVo().getCustomerCode());
        customerAddressBookVo.setWebshipId(bookingDataVo.getShipmentVo().getWebshipId());
        customerAddressBookVo.setContactName(addressVo.getContactName());
        customerAddressBookVo.setCompanyName(addressVo.getCompanyName());
        customerAddressBookVo.setAddress1(addressVo.getAddress());
        customerAddressBookVo.setAddress2(addressVo.getAddress2());
        customerAddressBookVo.setCity(addressVo.getCity());
        customerAddressBookVo.setState(addressVo.getState());
        customerAddressBookVo.setPostalCode(addressVo.getPostalCode());
        customerAddressBookVo.setCountry(addressVo.getCountry());
        customerAddressBookVo.setPhone(addressVo.getPhone());
        customerAddressBookVo.setEmail(addressVo.getEmail());
        return customerAddressBookVo;
    }
}
