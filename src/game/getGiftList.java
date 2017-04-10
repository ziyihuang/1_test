package game;

import interface_lib.Http;
import interface_lib.newHttp;
import net.sf.json.JSONObject;

/*
 *@author circle
 *@version 创建时间：2016年11月18日上午11:47:57
 */

public class getGiftList {
	private static Object[][] testobject = { 
			{802600647,
				"f0e6cf50c5aaa2648633099574568c317e06572dea30aed2766860893496c7ec", },
	};
	

	public static void main(String[] args) throws Exception {
		// 设置接口URL
		String Host = Http.gettesthost();
		String URL = "/jiawawa/gameGift/getGameGiftList";

		int t = testobject.length;// 读取测试环境用例数

		Object[] testcase;// for循环中存储测试用例

		// for循环读取并执行用例
		for (int i = 0; i < 1; i++) {
				testcase = testobject[i];
 
			// 生成接口请求参数的data部分
			long timestamp = System.currentTimeMillis();
			JSONObject data = new JSONObject();
			data.put("kugouId", testcase[0]);
			data.put("token", testcase[1]);
			data.put("timestamp", timestamp);
			data.put("channel", 1);
			data.put("appId", 1010);
			// 执行用例，获取请求的JSON和响应的JSON[也可能是"服务器返回502错误"等]
			String[] JSON = newHttp.jwwPost(Host+URL, data);
			JSON[1] = JSON[1].replace(",","\n");
			System.out.println(JSON[1]);
			// 打印请求的JSON
			System.out.println(JSON[0]);
			// 打印响应的JSON[格式化的JSON]
			System.out.println(JSON[1]);
		
//			// 打印响应的JSON[没格式化的JSON]
//			try {
//				System.out.println(JSONObject.fromObject(JSON[1]));
//			} catch (Exception e) {
//				// 有可能不是JSON格式的，则直接输出[如"服务器返回502错误"]
//				System.out.println(JSON[1]);
//			}


		}
	}
}