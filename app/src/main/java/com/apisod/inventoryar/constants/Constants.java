package com.apisod.inventoryar.constants;

public interface Constants {

    String MYPREF = "myPref";
    String DATABASE_NAME = "inventoryar_db";

    /*
     * =========================================================
     * ENVIRONMENT CONFIG
     * =========================================================
     */
    boolean IS_TEST_MODE = true;

    /*
     * =========================================================
     * FIRESTORE COLLECTIONS
     * =========================================================
     */
    String TEST_USER_CONTRIBUTION = "test_user_contribution";
    String USER_CONTRIBUTION = "user_contribution";
    String INVENTORY_AR = "inventoryAR";
    String USERS = "users";
    String BUILDINGS = "buildings";
    String ROOMS = "rooms";
    String INVENTORY_OBJECTS = "inventory_objects";
    String SCAN_SESSIONS = "scan_sessions";
    String ANCHORS = "anchors";

    /*
     * =========================================================
     * FIRESTORE ROOT PATH
     * =========================================================
     */
    static String getRootCollection() {
        return IS_TEST_MODE
                ? TEST_USER_CONTRIBUTION
                : USER_CONTRIBUTION;
    }
}