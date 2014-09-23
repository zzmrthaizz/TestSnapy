package network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.OkHttpClient;

import java.lang.reflect.Type;
import java.util.List;

import model.IsoCode;
import model.Operator;
import model.Session;
import model.User;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import util.ApiEndpoint;
import util.Logger;
import util.Utils;

public class NetworkClient {
	private static NetworkClient INSTANCE;

	public static NetworkClient getInstance() {
		if (INSTANCE == null)
			INSTANCE = new NetworkClient();
		return INSTANCE;
	}

	public NetworkClient() {
		super();

	}

	private void createClient() {

	}


	public String getToken(Context context, String username, String password) {
		OkHttpClient okHttpClient = new OkHttpClient();
		RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(ApiEndpoint.ROOT_API).setClient(new
				OkClient(okHttpClient)).build();
		NetworkService cJayService = restAdapter.create(NetworkService.class);
		JsonObject tokenJson = cJayService.getToken(username, password);
		String token = tokenJson.get("token").getAsString();
		return token;
	}


	public User getCurrentUser(Context context, String token) {
		OkHttpClient okHttpClient = new OkHttpClient();
		RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(ApiEndpoint.ROOT_API).setClient(new
				OkClient(okHttpClient)).build();
		NetworkService cJayService = restAdapter.create(NetworkService.class);
		String cJayVersion = Utils.getAppVersionName(context);
		User user = cJayService.getCurrentUser(token, cJayVersion);

		return user;

	}

	public List<IsoCode> getRepairCodes(Context context, String token, String lastModifiedDate) {
		OkHttpClient okHttpClient = new OkHttpClient();
		RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(ApiEndpoint.ROOT_API).setClient(new
				OkClient(okHttpClient)).build();
		NetworkService cJayService = restAdapter.create(NetworkService.class);
		String cJayVersion = Utils.getAppVersionName(context);
        List<IsoCode>  repairCodes = cJayService.getRepairCodes(token, cJayVersion, lastModifiedDate);
        Logger.e(repairCodes.get(0).display_name);
		return repairCodes;
	}

	public List<IsoCode> getDamageCodes(Context context, String token, String lastModifiedDate) {
		OkHttpClient okHttpClient = new OkHttpClient();
		RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(ApiEndpoint.ROOT_API).setClient(new
				OkClient(okHttpClient)).build();
		NetworkService cJayService = restAdapter.create(NetworkService.class);
		String cJayVersion = Utils.getAppVersionName(context);
        List<IsoCode> damageCodes = cJayService.getDamageCodes(token, cJayVersion, lastModifiedDate);
        Logger.e(damageCodes.get(0).display_name);
		return damageCodes;
	}

	public List<IsoCode> getComponentCodes(Context context, String token, String lastModifiedDate) {
		OkHttpClient okHttpClient = new OkHttpClient();
		RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(ApiEndpoint.ROOT_API).setClient(new
				OkClient(okHttpClient)).build();
		NetworkService cJayService = restAdapter.create(NetworkService.class);
		String cJayVersion = Utils.getAppVersionName(context);
        List<IsoCode> componentCodes = cJayService.getComponentCodes(token, cJayVersion, lastModifiedDate);
        Logger.e(componentCodes.get(0).display_name);
		return componentCodes;
	}

	public List<Operator> getOperators(Context context, String token, String lastModifiedDate) {
		OkHttpClient okHttpClient = new OkHttpClient();
		RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(ApiEndpoint.ROOT_API).setClient(new
				OkClient(okHttpClient)).build();
		NetworkService cJayService = restAdapter.create(NetworkService.class);
		String cJayVersion = Utils.getAppVersionName(context);
        List<Operator> operators = cJayService.getOperators(token, cJayVersion, lastModifiedDate);
        Logger.e(operators.get(0).Name);
		return operators;
	}

	public List<Session> getContainerSessionsByPage(Context context, String token,int page, String lastModifiedDate) {
		OkHttpClient okHttpClient = new OkHttpClient();
		RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(ApiEndpoint.ROOT_API).setClient(new
				OkClient(okHttpClient)).setLogLevel(RestAdapter.LogLevel.FULL).setLog(new RestAdapter.Log() {
            @Override
            public void log(String message) {
                Logger.e("Get Container: "+message);
            }
        }).build();
		NetworkService cJayService = restAdapter.create(NetworkService.class);
		String cJayVersion = Utils.getAppVersionName(context);
        JsonObject containerSessionsByPage = cJayService.getContainerSessionsByPage(token, cJayVersion, page, lastModifiedDate);
		JsonArray containerSessionsByPageAsJsonArray = containerSessionsByPage.getAsJsonArray("results");
		Logger.e(containerSessionsByPageAsJsonArray.toString());
		Gson gson = new Gson();
		Type listType = new TypeToken<List<Session>>(){}.getType();
		List<Session> containers = gson.fromJson(containerSessionsByPageAsJsonArray.toString(), listType);
        Logger.e(containers.toString());
		return containers;
	}

    public Session getContainerSessionById(Context context, String token,int id) {
        OkHttpClient okHttpClient = new OkHttpClient();
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(ApiEndpoint.ROOT_API).setClient(new
                OkClient(okHttpClient)).setLogLevel(RestAdapter.LogLevel.FULL).setLog(new RestAdapter.Log() {
            @Override
            public void log(String message) {
                Logger.e("getContainerSessionById: "+message);
            }
        }).build();
        NetworkService cJayService = restAdapter.create(NetworkService.class);
        String cJayVersion = Utils.getAppVersionName(context);
        Session containerSessionById = cJayService.getContainerSessionById(token, cJayVersion, id);
        Logger.e(containerSessionById.getContainerId());
        return containerSessionById;
    }
}
