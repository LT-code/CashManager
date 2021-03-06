package servlet;

import javax.servlet.annotation.WebServlet;

import servlet.function.payment.GetPaymentTypes;
import servlet.function.payment.PaymentCancel;
import servlet.function.payment.PaymentChoosePaymentType;
import servlet.function.payment.PaymentPay;
import utils.servlet.Route;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet("/payment/*")
public class PaymentServlet extends Servlet {
	private static final long serialVersionUID = 1L;

	private static final Route[] ROUTES =
		{
			new Route(Route.GET, API_ROUTE + "/payment/get_types", new GetPaymentTypes(), "", "", "Get all payment types avaiable."),
			new Route(Route.POST, API_ROUTE + "/payment/choose_payment_type", new PaymentChoosePaymentType(), "int idCart, int idType", "", "Choose a payment type."),
			new Route(Route.PUT, API_ROUTE + "/payment/pay", new PaymentPay(), "int idCart | if credit card : String number, String pin | if cheque : String code", "", "Make the payment if the payment type is choosen."),
			new Route(Route.DELETE, API_ROUTE + "/payment/cancel", new PaymentCancel(), "", "idCart=?", "Cancel the payment.")
		};
	
	@Override
	public Route[] getRoutes() {
		return ROUTES;
	}

	@Override
	public String getName() {
		return "Payment";
	}
}
