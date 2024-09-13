package pl.shelter.dto;

public class ValidationMessages {
    public static class Auth {
        public static final String LOGIN_BLANK = "{ACCOUNT.LOGIN_BLANK}";
        public static final String PASSWORD_BLANK = "{ACCOUNT.PASSWORD_BLANK}";
    }
    public static class Account {
        public static final String LOGIN_FORMAT = "{ACCOUNT.LOGIN_FORMAT}";
        public static final String LOGIN_LENGTH = "{ACCOUNT.LOGIN_LENGTH}";
        public static final String EMAIL_FORMAT = "{ACCOUNT.EMAIL_FORMAT}";
        public static final String PASSWORD_LENGTH = "{ACCOUNT.PASSWORD_LENGTH}";
        public static final String PERSONID_FORMAT = "{ACCOUNT.PERSONID_FORMAT}";
        public static final String PERSONID_LENGTH = "{ACCOUNT.PERSONID_LENGTH}";
        public static final String FIRSTNAME_LENGTH = "{ACCOUNT.FIRSTNAME_LENGTH}";
        public static final String LASTNAME_LENGTH = "{ACCOUNT.LASTNAME_LENGTH}";
    }
    public static class InvoiceData {
        public static final String ADDRESS_LENGTH = "{INVOICEDATA.BUSINESS_NAME_LENGTH}";
        public static final String NIP_FORMAT = "{INVOICEDATA.NIP_FORMAT}";
        public static final String NIP_LENGTH = "{INVOICEDATA.NIP_LENGTH}";
    }
}