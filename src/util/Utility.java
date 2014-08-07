package util;

import model.City;
import model.County;
import model.Province;
import android.text.TextUtils;
import db.MyWeatherDB;

public class Utility {
	
	/**
	 * Parser for Province
	 */
	
	public synchronized static boolean handleProvincesResponse(MyWeatherDB myWeatherDB, String response){
		if (!TextUtils.isEmpty(response)){
			String[] allProvinces = response.split(",");
			if (allProvinces != null && allProvinces.length > 0){
				for (String p : allProvinces){
					String[] array = p.split("\\|");
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					myWeatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Parser for City
	 */
	
	public static boolean handleCitiesResponse(MyWeatherDB myWeatherDB, String response, int provinceId){
		if(!TextUtils.isEmpty(response)){
			String[] allCities = response.split(",");
			if (allCities != null && allCities.length > 0){
				for (String c : allCities){
					String[] array = c.split("\\|");
					City city = new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					myWeatherDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Parse for County
	 */
	
	public static boolean handleCountiesResponse(MyWeatherDB myWeatherDB, String response, int cityId){
		if(!TextUtils.isEmpty(response)){
			String[] allCounties = response.split(",");
			if (allCounties != null && allCounties.length > 0){
				for (String c : allCounties){
					String[] array = c.split("\\|");
					County county = new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					myWeatherDB.saveCounty(county);
				}
				return true;
			}
		}
		return false;
	}
}
