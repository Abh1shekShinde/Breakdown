package controllers;

public class Session {
    private static int mechanic_id = -1;
    private static int id = -1;
    private static boolean user = false;

    public static void setId(int id) {
        Session.id = id;
    }

    public static void setMechanic_id(int mechanic_id) {
        Session.mechanic_id = mechanic_id;
    }

    public static int getId() {
        return id;
    }

    public static int getMechanic_id() {
        return mechanic_id;
    }

    public static boolean isUser() {
        return user;
    }

    public static void setUser(boolean user) {
        Session.user = user;
    }
}
