package test.com.gms.xms.persistence;

import com.gms.xms.filter.admin.AdminQuickSearchFilter;
import com.gms.xms.persistence.dao.admin.quicksearch.*;
import com.gms.xms.txndb.vo.account.contact.ManageContactVo;
import com.gms.xms.txndb.vo.admin.quicksearch.QuickSearchCustomerVo;
import com.gms.xms.txndb.vo.admin.quicksearch.QuickSearchInvoiceVo;
import com.gms.xms.txndb.vo.admin.quicksearch.QuickSearchShipmentBillingVo;
import com.gms.xms.txndb.vo.admin.quicksearch.QuickSearchShipmentVo;

import java.util.List;

/**
 * Posted from Apr 26, 2016 4:54:04 PM
 * <p>
 * Author huynd
 */
public class AdminQuickSearchDaoTest {
    public static void main(String[] args) throws Exception {
        // Quick search customers
        QuickSearchCustomerDao daoCustomer = new QuickSearchCustomerDao();
        AdminQuickSearchFilter filterCustomer = new AdminQuickSearchFilter();
        filterCustomer.setFranchiseList("101,102");
        filterCustomer.setSearchValue("101");
        long countCustomers = daoCustomer.countCustomersByCustomerCode(filterCustomer);
        List<QuickSearchCustomerVo> quickSearchCustomerVos = daoCustomer.getCustomersByCustomerCode(filterCustomer);
        System.out.println("Record count customers: " + countCustomers);
        System.out.println("Result list customers:\n" + quickSearchCustomerVos);
        // Quick search contacts
        QuickSearchContactDao daoContact = new QuickSearchContactDao();
        AdminQuickSearchFilter filterContact = new AdminQuickSearchFilter();
        filterContact.setProspectList("2409");
        filterContact.setSearchValue("abc");
        long countContacts = daoContact.countContactsByContact(filterContact);
        List<ManageContactVo> quickSearchContactVos = daoContact.getContactsByContact(filterContact);
        System.out.println("Record count contacts:\n " + countContacts);
        System.out.println("Result list contacts:\n" + quickSearchContactVos);
        // Quick search shipment billings
        QuickSearchShipmentBillingDao daoShipmentBilling = new QuickSearchShipmentBillingDao();
        AdminQuickSearchFilter filterShipmentBilling = new AdminQuickSearchFilter();
        filterShipmentBilling.setFranchiseList("102,106,114");
        filterShipmentBilling.setSearchValue("123");
        long countShipmentBillings = daoShipmentBilling.countShipmentBillingsByAirbill(filterShipmentBilling);
        List<QuickSearchShipmentBillingVo> quickSearchShipmentBillingVos = daoShipmentBilling.getShipmentBillingsByAirbill(filterShipmentBilling);
        System.out.println("Record count airbills:\n " + countShipmentBillings);
        System.out.println("Result list airbills:\n" + quickSearchShipmentBillingVos);
        // Quick search invoices
        QuickSearchInvoiceDao daoInvoice = new QuickSearchInvoiceDao();
        AdminQuickSearchFilter filterInvoice = new AdminQuickSearchFilter();
        filterInvoice.setFranchiseList("102,106,114");
        filterInvoice.setSearchValue("123");
        long countInvoices = daoInvoice.countInvoicesByInvoiceCode(filterInvoice);
        List<QuickSearchInvoiceVo> quickSearchInvoiceVos = daoInvoice.getInvoicesByInvoiceCode(filterInvoice);
        System.out.println("Record count invoices:\n " + countInvoices);
        System.out.println("Result list invoices:\n" + quickSearchInvoiceVos);
        // Quick search shipments
        QuickSearchShipmentDao daoShipment = new QuickSearchShipmentDao();
        AdminQuickSearchFilter filterShipment = new AdminQuickSearchFilter();
        filterShipment.setFranchiseList("102,106,114");
        filterShipment.setSearchValue("123");
        long countShipments = daoShipment.countShipmentsByAirbill(filterShipment);
        List<QuickSearchShipmentVo> quickSearchShipmentVos = daoShipment.getShipmentsByAirbill(filterShipmentBilling);
        System.out.println("Record count airbill labels:\n " + countShipments);
        System.out.println("Result list airbill labels:\n" + quickSearchShipmentVos);
        // Quick search webship detail

        // Quick search references
        QuickSearchReferenceDao daoReference = new QuickSearchReferenceDao();
        AdminQuickSearchFilter filterReference = new AdminQuickSearchFilter();
        filterReference.setFranchiseList("102,106,114");
        filterReference.setSearchValue("123");
        long countReferences = daoReference.countShipmentsByReference(filterReference);
        List<QuickSearchShipmentVo> quickSearchReferenceVos = daoReference.getShipmentsByReference(filterReference);
        System.out.println("Record count references:\n " + countReferences);
        System.out.println("Result list references:\n" + quickSearchReferenceVos);
        // Quick search references
        QuickSearchConfirmationNoDao daoConfirmationNo = new QuickSearchConfirmationNoDao();
        AdminQuickSearchFilter filterConfirmationNo = new AdminQuickSearchFilter();
        filterConfirmationNo.setFranchiseList("102,106,114");
        filterConfirmationNo.setSearchValue("123");
        long countConfirmationNos = daoConfirmationNo.countShipmentsByConfirmationNo(filterConfirmationNo);
        List<QuickSearchShipmentVo> quickSearchConfirmationNoVos = daoConfirmationNo.getShipmentsByConfirmationNo(filterConfirmationNo);
        System.out.println("Record count booking nos:\n " + countConfirmationNos);
        System.out.println("Result list booking nos:\n" + quickSearchConfirmationNoVos);
    }
}
