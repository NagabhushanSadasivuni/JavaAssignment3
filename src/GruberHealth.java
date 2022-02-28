import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GruberHealth {
    public static void main(String[] args) throws ParseException, InputMismatchException {



        List<Calendar> signUpDate = new ArrayList<>(); 		// List to Hold all Sign Up Dates
        List<Calendar> currentDate = new ArrayList<>();		// List to Hold all Current Dates

        //Created an Object to get required date format using SimpleDateFormat class
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        String[] input;
        int num;
        int inputLength=0;
        System.out.println("please enter an integer which is equal to number of inputs");
        Scanner scan = new Scanner(System.in);

        //Enters the loop once an integer is entered
        if (scan.hasNextInt() ) {

            num =  scan.nextInt();
            scan.nextLine(); //

            if (num>0) {

                String[][] formDate = new String[num][2];

                for (int i = 1; i <= num; i++) {

                    input = scan.nextLine().split(" "); //
                    inputLength = input.length;
                    if (inputLength==2) {


                        try {
                            //creating a calender instance for signUp
                            Calendar signUp = Calendar.getInstance();
                            signUp.setTime(sdf.parse(input[0]));

                            if (!sdf.format(signUp.getTime()).equals(input[0])) {
                                System.out.println("Invalid Date Entered");
                                break;
                            }
                            //if the date format matches the criteria then add to the list of signUpDate
                            signUpDate.add(signUp);
                        } catch (Exception e) {
                            System.out.println("Invalid input");
                            break;
                        }

                        try {
                            //creating calender instance for Current Date
                            Calendar current = Calendar.getInstance();
                            current.setTime(sdf.parse(input[1]));

                            if (!sdf.format(current.getTime()).equals(input[1])) {
                                System.out.println("Invalid Date Entered");
                                break;
                            }
                            //if the date format matches the criteria then add to the list of currentDate
                            currentDate.add(current);
                        } catch (Exception e) {
                            System.out.println("Invalid input");
                            break;
                        }

                    }
                    else {
                        System.out.println("Invalid Input");
                        break;
                    }

                }

                if (inputLength ==2) {


                    try {

                        for (int j = 0; j < num; j++) {

                            //cloning each and every date to  sign,signAnv=iversry,formDateStart,formDateEnd and manipulating them in accordance with requirements

                            Calendar sign = (Calendar) signUpDate.get(j).clone();
                            Calendar signAniv = (Calendar) signUpDate.get(j).clone();
                            Calendar formDateStart = (Calendar) signUpDate.get(j).clone();
                            Calendar formDateEnd = (Calendar) signUpDate.get(j).clone();

                            Calendar curr = (Calendar) currentDate.get(j).clone();


                            signAniv.add(Calendar.YEAR, 1);

                            formDateStart.add(Calendar.YEAR, 1);
                            formDateStart.add(Calendar.DAY_OF_YEAR, -30);

                            formDateEnd.add(Calendar.YEAR, 1);
                            formDateEnd.add(Calendar.DAY_OF_YEAR, 30);

                            if (curr.before(formDateStart)) {
                                formDate[j][0] = "No";
                                formDate[j][1] = "range";
                            }

                            else {
                                int signYear = sign.get(Calendar.YEAR);
                                int currYear = curr.get(Calendar.YEAR);

                                signAniv.add(Calendar.YEAR, currYear - signYear - 1);
                                formDateStart.add(Calendar.YEAR, currYear - signYear - 1);
                                formDateEnd.add(Calendar.YEAR, currYear - signYear - 1);

                                if (curr.before(formDateEnd)) {
                                    formDate[j][0] = sdf.format(formDateStart.getTime());
                                    formDate[j][1] = sdf.format(curr.getTime());
                                }

                                else {
                                    formDate[j][0] = sdf.format(formDateStart.getTime());
                                    formDate[j][1] = sdf.format(formDateEnd.getTime());
                                }

                            }

                        }

                        //Printing the desired result

                        for (int m = 0; m < num; m++) {

                            for (int n = 0; n < 2; n++) {
                                System.out.print(formDate[m][n] + " ");
                            }
                            System.out.println();
                        }


                    } catch (Exception e) {
                        //System.out.println(e);
                    }

                }

            }

            else {

                System.out.println("Invalid Number");
            }
        }


        else {
            System.out.println("Invalid Number Entered");

        }
        scan.close();


    }
}
