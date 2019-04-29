package com.gms.xms.common.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Posted from Jun 14, 2016 1:50:32 PM
 * <p>
 * Author dattrinh
 */
public class StarTrackConstants {
    public static final String STARTRACK_PICKUP_URL = "StarTrack pickup url";
    public static final String STARTRACK_TRACKING_URL = "StarTrack tracking url";
    public static final String STARTRACK_CONNOTE_URL = "StarTrack connote url";
    public static final String STARTRACK_GET_DEPOT_URL = "StarTrack getdepot url";
    public static final String STARTRACK_ACCOUNT = "StarTrack Account";
    public static final String STARTRACK_PASSWORD = "StarTrack Password";
    public static final String STARTRACK_USERNAME = "StarTrack Username";
    public static final String STARTRACK_TRACKING_USERNAME = "StarTrack Tracking Username";
    public static final String STARTRACK_TRACKING_PASSWORD = "StarTrack Tracking Password";
    public static final String STARTRACK_TRACKING_ACCOUNT_NO = "StarTrack Tracking Account No";
    public static final String STARTRACK_TRACKING_API_KEY = "StarTrack Tracking API Key";

    public static final Map<Integer, String> STARTRACK_BOOKING_ERRORS = new HashMap<Integer, String>() {
        private static final long serialVersionUID = 1L;

        {
            put(1, "Required a CustId or PrepaidNumber");
            put(2, "Cannot have both a CustID and a PrepaidNumber");
            put(3, "CustId should be 6-8 characters long");
            put(4, "PrepaidNumber should be 8 characters long");
            put(5, "First 3 characters of PrepaidNumber should be alphanumeric");
            put(6, "Last 5 characters of PrepaidNumber should be numeric");
            put(7, "ItemCount is required and should be between 1 and 99");
            put(8, "TotalWeight is required and should be between 1 and 9999");
            put(9, "TotalWeight > 20 so ItemLength, ItemWidth and ItemHeight should provide dimensions of largest Item");
            put(10, "PickupDate is a required field");
            put(11, "PickupTime is a required field and should be 5 characters long");
            put(12, "PickupLatest is a required field and should be 5 characters long ");
            put(13, "PickupDate is in an invalid format");
            put(14, "PickupTime is in an invalid format");
            put(15, "PickupLatest is in an invalid format");
            put(16, "Pickup is for earlier than Now");
            put(17, "PickupDate cannot be for a Saturday or Sunday");
            put(18, "PickupTime is within 1 hour of PickupLatest");
            put(19, "No pickups can be booked for after 5PM");
            put(20, "No pickups can be booked for before 8AM");
            put(21, "PickupLatest cannot be after 6PM");
            put(22, "PickupLatest cannot be before 9AM");
            put(23, "Sender Name, Addr1, City and PostCode are required");
            put(25, "Sender Phone, ContactName and ContactArea are required");
            put(26, "Sender Phone should be 10 characters long, numeric, Sender Contact details should be between 2 and 30 characters long");
            put(27, "Receiver Name, Addr1, City and PostCode are required");
            put(29, "Receiver Phone, ContactName and ContactArea are required");
            put(30, "Receiver Phone should be 10 characters long, numeric, Receiver Contact details should be between 2 and 30 characters long");
            put(31, "ItemDescription should not be longer than 30 characters");
            put(32, "SenderName should not be longer than 30 characters");
            put(33, "SenderAddr1 should not be longer than 40 characters");
            put(34, "SenderAddr2 should not be longer than 40 characters");
            put(35, "SenderCity should not be longer than 30 characters");
            put(36, "SenderPostCode should not be longer than 4 characters");
            put(37, "SenderPhone should be 10 digits long");
            put(38, "SenderContactName should not be longer than 30 characters");
            put(39, "SenderContactArea should not be longer than 30 characters");
            put(40, "ReceiverName should not be longer than 30 characters");
            put(41, "ReceiverAddr1 should not be longer than 40 characters");
            put(42, "ReceiverAddr2 should not be longer than 40 characters");
            put(43, "ReceiverCity should not be longer than 30 characters");
            put(44, "ReceiverPostCode should not be longer than 4 characters");
            put(45, "ReceiverPhone should be 10 digits long");
            put(46, "ReceiverContactName should not be longer than 30 characters");
            put(47, "ConNoteRequired should not be longer than 1 character");
            put(48, "CustReference should not be longer than 20 characters");
            put(49, "ShipInstructions should not be longer than 100 characters");
            put(50, "The ServiceType is not valid");
            put(51, "Notes should not be longer than 254 characters");
            put(52, "Trading issue with Customer");
            put(53, "Customer requires a CustReference");
            put(54, "Customer account not available - Deleted");
            put(55, "Sender Address is outside an StarTrack serviced area");
            put(56, "SenderCity and Postcode combination are invalid");
            put(57, "PrepaidNumer cannot begin with zero");
            put(58, "Invalid Customer ID");
            put(59, "Item Description is required");
            put(60, "Invalid characters in Sender Address");
            put(61, "Invalid characters in Receiver Address");
            put(62, "PickupDate is more than 30 days in the future");
            put(63, "Sender and Receiver phone numbers must only contain numbers (no spaces or other characters)");
            put(64, "General Peoplesoft Error");
            put(65, "Invalid characters in Sender/Receiver Phone");
            put(66, "ItemLength, ItemWidth and ItemHeight cannot be less than 0");
            put(67, "Pickup time is after the cutoff time for this Postcode. Retry for the next business day");
            put(68, "Invalid service type for your 8 digit account");
            put(69, "Invalid service type for your 7 digit account");
        }
    };

    public static final Map<Integer, String> STARTRACK_ECONNOTE_ERRORS = new HashMap<Integer, String>() {
        private static final long serialVersionUID = 1L;

        {
            put(1, "CID is invalid.");
            put(2, "CID must be 5 digits (eg. 00312).");
            put(3, "Articles must be between 1 and 99.");
            put(4, "Articles does not match ArticleList.");
            put(5, "EmailAddress must be supplied to EmailManifestReport or EmailLabels.");
            put(6, "Account is required.");
            put(7, "ServiceCode must be one character.");
            put(8, "ReceiverCompany is required.");
            put(9, "ReceiverAddress1 is required.");
            put(10, "ReceiverSuburb is required.");
            put(11, "ReceiverPostcode is required.");
            put(12, "ReceiverZone is required.");
            put(13, "ReceiverSticker is required.");
            put(14, "FromZone is required.");
            put(15, "FromPort is required.");
            put(16, "DeadWeight is required (whole kilos only) between 1 and 9999.");
            put(17, "SenderCompany is required, and must be less than 30 characters.");
            put(18, "SenderAddress is required.");
            put(19, "ManifestNumber must be 5 digits (eg. 00034).");
            put(20, "If Connotenum is supplied then ArticleList must also be supplied.");
            put(21, "If ArticleList is supplied then Connotenum must also be supplied.");
            put(22, "Invalid Service Code.");
            put(23, "Invalid Receiver Suburb and/or Postcode.");
            put(24, "Invalid Sender Suburb and/or Postcode.");
            put(25, "Sender Address 1 must be less than 40 characters.");
            put(26, "Sender Address 2 must be less than 40 characters.");
            put(27, "Sender Address 3 must be less than 40 characters.");
            put(28, "Receiver Address 1 must be less than 40 characters.");
            put(29, "Receiver Address 2 must be less than 40 characters.");
            put(30, "Receiver Address 3 must be less than 40 characters.");
            put(31, "Receiver Company Name must be less than 30 characters.");
            put(32, "Receiver Contact Name must be less than 30 characters.");
            put(33, "Receiver email must be less than 255 characters.");
            put(34, "Receiver Telephone must be less than 20 characters.");
            put(35, "Receiver Fax must be less than 20 characters.");
            put(36, "Receiver Mobile must be less than 20 characters.");
            put(37, "REF1 must be less than 20 characters.");
            put(38, "REF2 must be less than 20 characters.");
            put(39, "ATL is not allowed with Airlock or Deliver to Person Products.");
            put(40, "Sender and Receiver destination must be in the same Zone for a Local Service.");
            put(41, "Only 1, 3 or 5 kilos is allowed for a Prepaid service.");
            put(42, "Only 1 article is allowed for a Prepaid service.");
            put(43, "This Service cannot be used in Regional Areas.");
            put(44, "Invalid Lithium Batteries Type. Must be between 1 and 6.");
            put(45, "No Valid Article Ranges available for this service.");
            put(46, "Connotenum & ArticleNum must be a length of 8.");
            put(47, "Creator Name is required.");
            put(48, "Creator Phone must be at least 10 digits.");
            put(49, "The ManifestNumber specified has already been used.");
            put(50, "The Connotenum specified has already been used.");
            put(51, "The Connotenum cannot be the same as the ArticleList.");
            put(52, "The Connotenum cannot be the same as the ArticleList.");
            put(53, "Manifest Number must be numeric.");
            put(54, "Email Labels must be Y or N.");
            put(55, "Parcel Protection value must be between 1 and 9999.");
            put(56, "Error generating Connotenum for this service.");
            put(57, "Error generating Article numbers for this service.");
            put(58, "The weight for this service must be 15 kgs.");
            put(59, "Duplicate articles detected.");
            put(60, "Receiver Contact Name is required.");
            put(61, "Receiver Suburb is required.");
            put(62, "Receiver Postcode is required.");
            put(63, "Sender Suburb is required.");
            put(64, "Sender Postcode is required.");
            put(65, "Length, Width and Height must be between 1 and 9999.");
            put(66, "DeadWeight must be between 1 and 9999.");
            put(67, "CubicWeight must be between 1 and 9999.");
            put(68, "ATL must be Y or N.");
            put(69, "CubicVolume must be less than 999999999.");
            put(70, "Cubic weight must be equal to or under the prepaid weight (1, 3 or 5kg).");
            put(71, "Articlenum must be a length of 8.");
            put(72, "Your account is restricted to using whole kilos only.");
            put(73, "An Articlenum specified has already been used.");
            put(74, "Invalid 3 letter Product Code.");
            put(75, "Invalid Delivery Window. Allowed values are 00, 01, 02.");
            put(76, "Invalid Unit Type. Allowed values are CTN, SAT, PAL.");
            put(77, "Invalid Controlled Returns Print Flag. Allowed values are S or D (sender or depot).");
            put(78, "Controlled Returns cannot be used with Premium Home Delivery Services.");
            put(79, "Mandatory fields missing for Controlled Returns.");
            put(80, "Sender and Receiver email missing.");
            put(81, "Invalid Controlled Returns Service type. Allowed values are T or R.");
            put(82, "Mandatory fields missing for Dangerous Goods.");
            put(83, "StarTrack account is mandatory for PHD services.");
            put(84, "StarTrack account is mandatory for SMS Notify.");
            put(85, "StarTrack account is mandatory for Email Notify.");
            put(86, "Email or SMS Notify can only be used with Premium Home Delivery Services.");
            put(87, "Book In Deliver Flag must be Y or N.");
            put(88, "Book In Deliver Flag also requires Book In Not Before or Book In Not After.");
            put(89, "A Delivery Window is not allowed for this Receiver Suburb and Postcode.");
            put(90, "Book In Not Before cannot be before the Book In Not After date.");
            put(91, "Book In Not After cannot be after the Book In Not Before date.");
            put(93, "A Manifest Range has not been configured for your Account. Please contact the service desk.");
            put(94, "One of the following dates is not valid: BookInNotBefore/BookInNotAfter/DeliverOnDate.");
            put(95, "Connote Ranges have not been configured for your Account. Please contact the service desk.");
            put(96, "Product Code is mandatory for 8 digit StarTrack Accounts.");
            put(97, "A Returns Service Type is not valid unless the Return Label Print Flag is set correctly.");
            put(98, "A Unit Type is required.");
            put(99, "Account is invalid.");
        }
    };

    public static final String ORIGIN_ZONE = "0";
    public static final String ORIGIN_ZONE_NAME = "1";
    public static final String DESTINATION_ZONE_NAME = "2";
    public static final String DESTINATION_ZONE = "3";
    public static final String BASIC_CHARGE = "4";
    public static final String KG_RATE = "5";
    public static final String MIN_CHARGE = "6";
}