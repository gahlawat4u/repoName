import com.gms.delivery.dhl.service.DhlServiceClient;
import com.gms.delivery.dhl.xmlpi.datatype.capability.response.DCTResponse;
import com.gms.delivery.dhl.xmlpi.datatype.error.response.Condition;
import com.gms.delivery.dhl.xmlpi.datatype.error.response.DhlErrorResponse;
import com.gms.xms.common.utils.AppUtils;

public class Test {
    public static void main(String[] args) throws Exception {
        DhlServiceClient client = new DhlServiceClient();
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><p:DCTRequest xmlns:p=\"http://www.dhl.com\" xmlns:p1=\"http://www.dhl.com/datatypes\" xmlns:p2=\"http://www.dhl.com/DCTRequestdatatypes\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.dhl.com DCT-req.xsd \">	<GetCapability>		<Request>			<ServiceHeader>				<MessageTime>2016-01-13T17:04:33+10:00</MessageTime>				<MessageReference>14526686730913123414526686730913</MessageReference>				<SiteID>Dap0071prod</SiteID>				<Password>ceNbP21G</Password>			</ServiceHeader>		</Request>		<From>			<CountryCode>AU</CountryCode>			<Postalcode>4000</Postalcode>			<City>BRISBANE</City>		</From>		<BkgDetails>								  <PaymentCountryCode>AU</PaymentCountryCode>								  <Date>2016-01-14</Date>								  <ReadyTime>PT13H21M</ReadyTime>								  <ReadyTimeGMTOffset>+10:00</ReadyTimeGMTOffset>								  <DimensionUnit>CM</DimensionUnit>								  <WeightUnit>KG</WeightUnit>								  <Pieces>									<Piece>									  <PieceID>1</PieceID>										<Weight>5</Weight>									</Piece>								  </Pieces>      								  <IsDutiable>Y</IsDutiable>								  <NetworkTypeCode>AL</NetworkTypeCode>								</BkgDetails>			<To>				<CountryCode>				</CountryCode>					<Postalcode>BT14LB</Postalcode>					<City>BELFAST</City>				</To>				<Dutiable>											  <DeclaredCurrency>AUD</DeclaredCurrency>											  <DeclaredValue>52.50</DeclaredValue>											</Dutiable>				</GetCapability>			</p:DCTRequest>";
        String xmlResponse = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><res:ErrorResponse xmlns:res='http://www.dhl.com' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:schemaLocation= 'http://www.dhl.com err-res.xsd'>    <Response>        <ServiceHeader>            <MessageTime>2016-01-13T08:03:03+01:00</MessageTime>            <MessageReference>14526686730913123414526686730913</MessageReference>            <SiteID>Dap0071prod</SiteID>            <Password>Dap0071prod</Password>        </ServiceHeader>        <Status>            <ActionStatus>Error</ActionStatus>            <Condition>                <ConditionCode>111</ConditionCode>                <ConditionData>Error in parsing request XML:Error:                    Datatype error: In element &apos;CountryCode&apos; :                    Value &apos;     &apos; with length &apos;5&apos; is                    not equal to length facet &apos;2&apos;.. at line                    50, column 20</ConditionData>            </Condition>        </Status>    </Response></res:ErrorResponse>";
        xmlResponse = client.executeGetCapability(xml, xmlResponse);
        @SuppressWarnings("unused")
        DCTResponse dctResponse = null;
        DhlErrorResponse errorResponse = null;
        if (AppUtils.checkXmlTagValue(xmlResponse, "ActionStatus", "Error")) {
            errorResponse = AppUtils.xmlString2Object(xmlResponse, DhlErrorResponse.class);
        } else {
            dctResponse = AppUtils.xmlString2Object(xmlResponse, DCTResponse.class);
        }
        if (errorResponse != null) {
            System.out.println(errorResponse.getResponse().getStatus().getActionStatus());
            for (Condition condition : errorResponse.getResponse().getStatus().getCondition()) {
                System.out.println(condition.getConditionCode() + ":" + condition.getConditionData());
            }
        }
    }
}
