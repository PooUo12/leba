package client.validators;

import utils.collections.Coordinates;
import utils.collections.LocationFrom;
import utils.collections.LocationTo;
import utils.exceptions.IncorrectInputInScriptException;
import utils.exceptions.MustBeNotEmptyException;
import utils.exceptions.NotInDeclaredLimitsException;

import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * This class is used to ask the user for input
 */
public class RouteAsker {

    public final float Dis = 1;

    Scanner userScanner;
    private final boolean scriptMode;



    public RouteAsker(Scanner userScanner) {
        this.userScanner = userScanner;
        scriptMode = false;
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
            double max_X = 99;
            try {
                Console.print("Enter Coordinate X:");
                Console.print(Console.PS2);
                sX = userScanner.nextLine().trim();
                if(scriptMode) Console.printLn(sX);
                x = Double.parseDouble(sX);
                if ( x > max_X) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException e) {
                Console.printError("The X can't be loaded or recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();}
                catch (NotInDeclaredLimitsException e) {
                Console.printError("The X shouldn't be more than or equal " + max_X);
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
            int min_Y = -523;
            try {
                Console.print("Enter Coordinate Y:");
                Console.print(Console.PS2);
                sY = userScanner.nextLine().trim();
                if(scriptMode) Console.printLn(sY);
                if (sY.equals("")) throw new MustBeNotEmptyException();
                y = Long.parseLong(sY);
                if ( y <= min_Y) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException e) {
                Console.printError("The Y can't be loaded or recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();}
            catch (NotInDeclaredLimitsException e) {
                Console.printError("The Y should be more than " + min_Y);
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