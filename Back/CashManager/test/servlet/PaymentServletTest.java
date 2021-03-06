package servlet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import tables.PaymentTypeTable;
import utils.servlet.HttpStatus;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaymentServletTest extends ServletTest {
	@Test 
	public void test_getListTypes() {		
		JSONObject res = sendGet("/payment/get_types", "", null, lambdaTokenMachine);
		assertEquals(2, res.getJSONArray("data").length());
		assertEquals(PaymentTypeTable.TYPE_CREDIT_CARD, res.getJSONArray("data").getJSONObject(0).get("name"));
		assertEquals(PaymentTypeTable.TYPE_CHEQUE, res.getJSONArray("data").getJSONObject(1).get("name"));
		assertEquals(HttpStatus.SUCCESS, res.get("status"));
	}
	
	@Test 
	public void test_1_choosePaymentType() {		
		JSONObject params = new JSONObject();
		params.put("idCart", lambdaIdCart);
		params.put("idType", 1);
		JSONObject res = sendPost("/payment/choose_payment_type", "", params, lambdaTokenMachine);
		assertEquals(1, res.getJSONArray("data").getJSONObject(0).get("idType"));
		assertEquals(HttpStatus.SUCCESS, res.get("status"));
	}
	
	@Test 
	public void test_2_choosePaymentType_2() {		
		JSONObject params = new JSONObject();
		params.put("idCart", lambdaIdCart);
		params.put("idType", 2);
		JSONObject res = sendPost("/payment/choose_payment_type", "", params, lambdaTokenMachine);
		assertEquals(2, res.getJSONArray("data").getJSONObject(0).get("idType"));
		assertEquals(HttpStatus.SUCCESS, res.get("status"));
	}
	
	@Test
	public void test_3_cancelPayment() {
		JSONObject res = sendDelete("/payment/cancel", "idCart=" + lambdaIdCart, null, lambdaTokenMachine);
		assertEquals(2, res.getJSONArray("data").getJSONObject(0).get("idType"));
		assertEquals(HttpStatus.SUCCESS, res.get("status"));
	}
	
	@Test 
	public void test_4_payWithoutChoosingPayment() {		
		JSONObject params = new JSONObject();
		params.put("idCart", lambdaIdCart);
		params.put("number", "78269370980");
		params.put("pin", "1234");
		JSONObject res = sendPut("/payment/pay", "", params, lambdaTokenMachine);
		assertEquals(HttpStatus.INTERNAL_ERROR, res.get("status"));
	}
	
	@Test 
	public void test_5_choosePaymentType() {		
		JSONObject params = new JSONObject();
		params.put("idCart", lambdaIdCart);
		params.put("idType", 1);
		JSONObject res = sendPost("/payment/choose_payment_type", "", params, lambdaTokenMachine);
		assertEquals(1, res.getJSONArray("data").getJSONObject(0).get("idType"));
		assertEquals(HttpStatus.SUCCESS, res.get("status"));
	}
	
	@Test 
	public void test_6_payWrongNumber() {		
		JSONObject params = new JSONObject();
		params.put("idCart", lambdaIdCart);
		params.put("number", "78269370980");
		params.put("pin", "1234");
		JSONObject res = sendPut("/payment/pay", "", params, lambdaTokenMachine);
		assertEquals(HttpStatus.BAD_REQUEST, res.get("status"));
	}
	
	@Test
	public void test_7_payWrongPin() {		
		JSONObject params = new JSONObject();
		params.put("idCart", lambdaIdCart);
		params.put("number", "62328736792");
		params.put("pin", "4321");
		JSONObject res = sendPut("/payment/pay", "", params, lambdaTokenMachine);
		assertEquals(HttpStatus.BAD_REQUEST, res.get("status"));
	}
	
	@Test 
	public void test_8_payCreditCard() {		
		JSONObject params = new JSONObject();
		params.put("idCart", lambdaIdCart);
		params.put("number", "62328736792");
		params.put("pin", "1234");
		JSONObject res = sendPut("/payment/pay", "", params, lambdaTokenMachine);
		assertEquals(HttpStatus.SUCCESS, res.get("status"));
	}
	
	@Test 
	public void test_91_payChequeNotChoosen() {		
		JSONObject params = new JSONObject();
		params.put("idCart", lambdaIdCart);
		params.put("code", "62328736792");
		JSONObject res = sendPut("/payment/pay", "", params, lambdaTokenMachine);
		assertEquals(HttpStatus.INTERNAL_ERROR, res.get("status"));
	}
	
	@Test 
	public void test_92_choosePaymentType() {		
		JSONObject params = new JSONObject();
		params.put("idCart", lambdaIdCart);
		params.put("idType", 2);
		JSONObject res = sendPost("/payment/choose_payment_type", "", params, lambdaTokenMachine);
		assertEquals(2, res.getJSONArray("data").getJSONObject(0).get("idType"));
		assertEquals(HttpStatus.SUCCESS, res.get("status"));
	}
	
	@Test
	public void test_93_payWrongCode() {		
		JSONObject params = new JSONObject();
		params.put("idCart", lambdaIdCart);
		params.put("code", "77527836939323");
		JSONObject res = sendPut("/payment/pay", "", params, lambdaTokenMachine);
		assertEquals(HttpStatus.BAD_REQUEST, res.get("status"));
	}
	
	@Test 
	public void test_94_payCheque() {		
		JSONObject params = new JSONObject();
		params.put("idCart", lambdaIdCart);
		params.put("code", "67527836939323");
		JSONObject res = sendPut("/payment/pay", "", params, lambdaTokenMachine);
		assertEquals(HttpStatus.SUCCESS, res.get("status"));
	}
}