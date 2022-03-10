package helper;

public class WrongInputTextException extends Exception {
    protected String detail;
    protected static String lengthOut = "Недопустимое кол-во символов, MAX = 20";
    protected static String wrongSymbol = "Содержатся недопустимые символы! Только [a-z], [A-Z] [1-9] '_' ";

    public static class WrongLoginException extends WrongInputTextException {

        public WrongLoginException(String detail) {
            super();
            this.detail = detail;
        }

        @Override
        public String toString() {
            return "\nОШИБКА!\n" +
                    "detail= " + detail + "\n";
        }
    }

    public static class WrongPasswordException extends WrongInputTextException {

        public WrongPasswordException(String detail) {
            super();
            this.detail = detail;
        }

        @Override
        public String toString() {
            return "\nОШИБКА!\n" +
                    "detail= " + detail + "\n";
        }
    }
}
