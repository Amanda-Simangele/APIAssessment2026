package PayloadBuilder;

import Common.Authorization;
import Common.DataGenerator;
import org.json.simple.JSONObject;

public class NdosiPayload {

    //method to create user payload for registration
    public static JSONObject createUserPayload() {
        JSONObject payload = new JSONObject();
        payload.put("firstName", DataGenerator.name);
        payload.put("lastName", DataGenerator.surname);
        payload.put("email", DataGenerator.email);
        payload.put("password", Authorization.password);
        payload.put("confirmPassword", Authorization.password);
        payload.put("groupId", "1deae17a-c67a-4bb0-bdeb-df0fc9e2e526");
        return payload;
    }
    //method to login user payload for login
    public static JSONObject loginPayload(String email, String password) {
        JSONObject payload = new JSONObject();
        payload.put("email", email);
        payload.put("password", password);
        return payload;
    }
    //method to update user role payload for updating user role
    public static JSONObject updateUserRolePayload(String role) {
        JSONObject payload = new JSONObject();
        payload.put("role", role);
        return payload;
    }




}
