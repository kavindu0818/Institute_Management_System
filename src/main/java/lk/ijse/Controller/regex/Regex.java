package lk.ijse.Controller.regex;

import java.util.regex.Pattern;

public class Regex {
    private static final Pattern namePattern = Pattern.compile("^[a-zA-Z '.-]{4,}$");
    private static final Pattern idPattern = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$");
    private static final Pattern RegistrationCodePattern = Pattern.compile("^R\\d{4}$");
    private static final Pattern empRegistrationCodePattern = Pattern.compile("^E\\d{4}$");

    private static final Pattern emailPattern = Pattern.compile("(^[a-zA-Z0-9_.-]+)@([a-zA-Z]+)([\\.])([a-zA-Z]+)$");
    private static final Pattern cityPattern = Pattern.compile("[a-zA-Z]{4,}$");
    private static final Pattern mobilePattern = Pattern.compile("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$");
    private static final Pattern postalCodePattern = Pattern.compile("^\\d{5}$");
    private static final Pattern oldIDPattern = Pattern.compile("^[0-9]{9}[vVxX]$");
    private static final Pattern doublePattern = Pattern.compile("^[0-9]+\\.?[0-9]*$");

    private static final Pattern intPattern = Pattern.compile("^[1-9][0-9]?$|^100$");

    private static final Pattern agePattern = Pattern.compile("^\\d{1,3}$");
    private static final Pattern imageFilePattern = Pattern.compile("^.+\\.(?i)(jpg|jpeg|png|gif|bmp|tiff)$");

    private static final Pattern subjectCodePattern = Pattern.compile("^[A-Z]+\\d+$");

    private static final Pattern datePattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    private static final Pattern codePattern = Pattern.compile("^S\\d{4}$");
    private static final Pattern classAttendanceIDPattern = Pattern.compile("^SA\\d{4}$");

    private static final Pattern usercodePattern = Pattern.compile("^U\\d{4}$");
    private static final Pattern addressPattern = Pattern.compile("[a-zA-Z]{4,}");


    private static final Pattern gradePattern = Pattern.compile("^Grade\\s\\d{1,2}$");

    public static Pattern getAddressPattern() {
        return addressPattern;
    }
    private static final Pattern empAttencodePattern = Pattern.compile("^EA[0-9]{4,10}$");
    private static final Pattern CourseIDAttencodePattern = Pattern.compile("^CSA[0-9]{4,10}$");
    private static final Pattern digitPattern = Pattern.compile("^[1-9][0-9]*$");
    private static final Pattern toutorcodePattern = Pattern.compile("^T\\d{4}$");

    public static Pattern getToutorcodePattern(){return toutorcodePattern;}
    public static Pattern getUsercodePattern(){return usercodePattern;}
    private static final Pattern subjectcodePattern = Pattern.compile("^S\\d{4}$");


    public static Pattern getSubjectcodePattern(){return subjectcodePattern;}
    public static Pattern getCourseIDAttencodePattern(){return CourseIDAttencodePattern;}
    public static Pattern getDigitPattern() {
        return digitPattern;
    }

    public static Pattern getClassIDPattern(){return classAttendanceIDPattern;}

    public static Pattern getEmpAttencodePattern() {
        return empAttencodePattern;
    }
    public static Pattern getDatePattern() {return datePattern;}
    public static Pattern getGradePattern() {return gradePattern;}

    public static Pattern getCodePattern() {return codePattern;}

    public static Pattern getSubjectCodePattern() {return subjectCodePattern;}

    public static Pattern getImageFilePattern() {return imageFilePattern;}


    public static Pattern getAgePattern(){return agePattern;}



    public static Pattern getRegistrationCodePattern() {return RegistrationCodePattern;}



    public static Pattern getIntPattern() {return intPattern;}
    public static Pattern getOldIDPattern() {return oldIDPattern;}
    public static Pattern getPostalCodePattern() {return postalCodePattern;}
    public static Pattern getEmpRegistrationCodePattern() {return empRegistrationCodePattern;}
    public static Pattern getMobilePattern() {
        return mobilePattern;
    }
    public static Pattern getCityPattern() {
        return cityPattern;
    }
    public static Pattern getEmailPattern() {
        return emailPattern;
    }
    public static Pattern getNamePattern() {
        return namePattern;
    }
    public static Pattern getIdPattern() {
        return idPattern;
    }

    public static Pattern getDoublePattern() {
        return doublePattern;
}
}

