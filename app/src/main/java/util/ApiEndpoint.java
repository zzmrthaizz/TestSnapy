package util;

public class ApiEndpoint {

	public static final String ROOT_API = "https://cloudjay-web.appspot.com";
	public static final String TOKEN_API = "/api-token-auth/";
	public static final String API_ADD_GCM_DEVICE_API = "/mobile/gcm-devices.json";
	public static final String CURRENT_USER_API = "/api/cjay/current-user.json";
	public static final String LIST_OPERATORS_API = "/api/cjay/container-operators.json";
	public static final String LIST_DAMAGE_CODES_API = "/api/cjay/damage-codes.json";
	public static final String LIST_REPAIR_CODES_API = "/api/cjay/repair-codes.json";
	public static final String LIST_COMPONENT_CODES_API = "/api/cjay/component-codes.json";
	public static final String CONTAINER_SESSIONS_API = "/api/cjay/container-sessions.json";
	public static final String CONTAINER_SESSION_ITEM_API = "/api/cjay/container-sessions/{id}.json";

//	public static final String CJAY_TMP_STORAGE = "https://www.googleapis.com/upload/storage/v1beta2/b/cjaytmp/o?uploadType=media&name=%s";
	public static final String CJAY_TMP_STORAGE = "https://www.googleapis.com/upload/storage/v1beta2/b/cjaytmp/o";
}
