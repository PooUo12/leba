package managers;

import collections.Coordinates;
import collections.LocationFrom;
import collections.LocationTo;
import exceptions.IncorrectInputInScriptException;
import exceptions.MustBeNotEmptyException;
import exceptions.NotInDeclaredLimitsException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * This class is used to ask the user for input
 */
public class RouteAsker {

    private final double Max_X = 99;
    private final int Min_Y = -523;
    public final float Dis = 1;

    CollectionManager collectionManager;
    Scanner userScanner;
    private boolean scriptMode;
    private float distance;

    /**
     * This function returns a Scanner object that is used to read user input
     *
     * @return The Scanner object that is created in the method.
     */
    public Scanner getUserScanner() {
        return userScanner;
    }

    /**
     * This function sets the scanner object that will be used to read user input
     *
     * @param userScanner The Scanner object that will be used to read user input.
     */
    public void setUserScanner(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    public RouteAsker(CollectionManager collectionManager, Scanner userScanner) {
        this.userScanner = userScanner;
        this.collectionManager = collectionManager;
        scriptMode = false;
    }

    /**
     * This function is used to set the script mode to true
     */
    public void setScriptMode(){
        scriptMode = true;
    }
    /**
     * This function sets the scriptMode variable to false, which means that the user is in control of
     * the CLI
     */
    public void setUserMode(){
        scriptMode = false;
    }


    /**
     * This function generates a new id for a collection
     *
     * @return The id of the new collection.
     */
    public int setId() {
        return collectionManager.generateNewIdForCollection();
    }


    /**
     * Ask the user for a name and return it
     *
     * @return The name of the user.
     */
    public String askName() throws IncorrectInputInScriptException {
        String name;
        while (true) {
            Console.print("Enter name:");
            Console.print(Console.PS2);
            try {
                name = userScanner.nextLine().trim();
                if(scriptMode) Console.printLn(name);
                if (name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (MustBeNotEmptyException e) {
                Console.printError("The name can't be empty");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NoSuchElementException e) {
                Console.printError("The name can't be loaded or recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!userScanner.hasNext()) {
                    Console.printError("Ctrl-D Caused exit!");
                    System.exit(0);
                }
            } catch (IllegalStateException e) {
                Console.printError("Unexpected error!");
                System.exit(0);
            }
        }
        return name;
    }


    /**
     * Ask for the X coordinate of the point
     *
     * @return The X value.
     */
    private double askX() throws IncorrectInputInScriptException {
        double x;
        String sX;
        while (true) {
            try {
                Console.print("Enter Coordinate X:");
                Console.print(Console.PS2);
                sX = userScanner.nextLine().trim();
                if(scriptMode) Console.printLn(sX);
                x = Double.parseDouble(sX);
                if ( x > Max_X) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException e) {
                Console.printError("The X can't be loaded or recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();}
                catch (NotInDeclaredLimitsException e) {
                Console.printError("The X shouldn't be more than or equal " + Max_X);
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException e) {
                Console.printError("The X have to be an Double value");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            }
        }
        return x;
    }


    /**
     * The function askY() asks the user to enter the Y coordinate of the point
     *
     * @return The Y value.
     */
    private Long askY() throws IncorrectInputInScriptException {
        long y;
        String sY;
        while (true) {
            try {
                Console.print("Enter Coordinate Y:");
                Console.print(Console.PS2);
                sY = userScanner.nextLine().trim();
                if(scriptMode) Console.printLn(sY);
                if (sY.equals("")) throw new MustBeNotEmptyException();
                y = Long.parseLong(sY);
                if ( y <= Min_Y) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException e) {
                Console.printError("The Y can't be loaded or recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();}
            catch (NotInDeclaredLimitsException e) {
                Console.printError("The Y should be more than " + Min_Y);
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException e) {
                Console.printError("The Y have to be an Long value");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            } catch (MustBeNotEmptyException e) {
                Console.printError("The Y can't be empty");
                if (scriptMode) throw new IncorrectInputInScriptException();
            }
        }
        return y;
    }


    /**
     * AskCoordinates()
     *
     * @return A Coordinates object.
     */
    public Coordinates askCoordinates() throws IncorrectInputInScriptException {
        double x;
        long y;
        x = askX();
        y = askY();
        return new Coordinates(x, y);
    }



    /**
     * It enters a date and time.
     *
     * @return LocalDateTime.
     */
    public LocalDate askCreationDate() {
        while (true) {
            try {
                return LocalDate.now();
            } catch (DateTimeException e) {
                Console.printError("Problem with local data");
            }
        }
    }

    /**
     * Ask for a name and return it
     *
     * @return The method returns a string.
     */
    private String askname() throws IncorrectInputInScriptException {
        String name;
        while (true) {
            try {
                Console.print("Enter street:");
                Console.print(Console.PS2);
                name = userScanner.nextLine().trim();
                if (scriptMode) Console.printLn(name);
                if (name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (MustBeNotEmptyException e) {
                Console.printError("The name can't be empty");
            } catch (NoSuchElementException exception) {
                Console.printError("Name can't be recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            }
        }
        return name;
    }

    /**
     * Ask for the X in LocationFrom of the point
     *
     * @return The X value.
     */
    private float askx() throws IncorrectInputInScriptException {
        float x;
        while (true) {
            try {
                Console.print("Enter coordinate Location From X:");
                Console.print(Console.PS2);
                String s = userScanner.nextLine().trim();
                if(scriptMode) Console.printLn(s);
                x = Float.parseFloat(s);
                break;
            } catch (NoSuchElementException e) {
                Console.printError("The X can't be loaded or recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException e) {
                Console.printError("The X have to be an Float value");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            }
        }
        return x;
    }
    /**
     * Ask for the Y in LocationFrom of the point
     *
     * @return The Y value.
     */
    private float asky() throws IncorrectInputInScriptException {
        float y;
        while (true) {
            try {
                Console.print("Enter coordinate Location From Y:");
                Console.print(Console.PS2);
                String s = userScanner.nextLine().trim();
                if(scriptMode) Console.printLn(s);
                y = Float.parseFloat(s);
                break;
            } catch (NoSuchElementException e) {
                Console.printError("The Y can't be loaded or recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException e) {
                Console.printError("The Y have to be an Float value");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            }
        }
        return y;
    }
    /**
     * AskLocationFrom()
     *
     * @return A LocationFrom object.
     */
    public LocationFrom askLocationFrom() throws IncorrectInputInScriptException {
        float x;
        float y;
        String name;
        x = askx();
        y = asky();
        name = askname();
        return new LocationFrom(name,y,x);
    }
    /**
     * Ask for a name and return it
     *
     * @return The method returns a string.
     */
    private String ask_name() throws IncorrectInputInScriptException {
        String name;
        while (true) {
            try {
                Console.print("Enter street:");
                Console.print(Console.PS2);
                name = userScanner.nextLine().trim();
                if(scriptMode) Console.printLn(name);
                if (name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (MustBeNotEmptyException e) {
                Console.printError("The name can't be empty");
            } catch (NoSuchElementException exception) {
                Console.printError("Name can't be recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!userScanner.hasNext()) {
                    Console.printError("Ctrl-D Caused exit!");
                    System.exit(0);
                }
            } catch (IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            }
        }
        return name;
    }

    /**
     * Ask for the X in Location To of the point
     *
     * @return The X value.
     */
    private int ask_x() throws IncorrectInputInScriptException {
        int x;
        while (true) {
            try {
                Console.print("Enter coordinate Location To X:");
                Console.print(Console.PS2);
                String s = userScanner.nextLine().trim();
                if(scriptMode) Console.printLn(s);
                x = Integer.parseInt(s);
                break;
            } catch (NoSuchElementException e) {
                Console.printError("The X can't be loaded or recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException e) {
                Console.printError("The X have to be an Float value");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            }
        }
        return x;
    }
    /**
     * Ask for the Y in Location To of the point
     *
     * @return The Y value.
     */
    private long ask_y() throws IncorrectInputInScriptException {
        long y;
        while (true) {
            try {
                Console.print("Enter coordinate Location To Y:");
                Console.print(Console.PS2);
                String s = userScanner.nextLine().trim();
                if(scriptMode) Console.printLn(s);
                y = Long.parseLong(s);
                break;
            } catch (NoSuchElementException e) {
                Console.printError("The Y can't be loaded or recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!userScanner.hasNext()) {
                    Console.printError("Ctrl-D Caused exit!");
                    System.exit(0);
                }
            } catch (NumberFormatException e) {
                Console.printError("The Y have to be an Float value");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            }
        }
        return y;
    }

    /**
     * AskLocationTo()
     *
     * @return A LocationTo object.
     */
    public LocationTo askLocationTo() throws IncorrectInputInScriptException {
        int x;
        long y;
        String name;
        x = ask_x();
        y = ask_y();
        name = ask_name();
        return new LocationTo(name,x, y);
    }

    public float askDistance() throws IncorrectInputInScriptException {
        float dis;
        while (true) {
            try {
                Console.print("Enter distance:");
                Console.print(Console.PS2);
                String s = userScanner.nextLine().trim();
                if (scriptMode) Console.printLn(s);
                dis = Float.parseFloat(s);
                if (dis < Dis) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException e) {
                Console.printError("The distance can't be loaded or recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException e) {
                Console.printError("The distance should be more than " + Dis);
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException e) {
                Console.printError("The distance have to be an integer value");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            }
        }
        return dis;
    }
    }