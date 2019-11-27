package servlet;

import javax.servlet.annotation.WebServlet;

import servlet.function.payment.GetPaymentTypes;
import servlet.function.payment.PaymentCancel;
import servlet.function.payment.PaymentChooseType;
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
			new Route(Route.GET, API_ROUTE + "/payment/get_types", new GetPaymentTypes(), "", ""),
			new Route(Route.POST, API_ROUTE + "/payment/choose_type", new PaymentChooseType(), "", ""),
			new Route(Route.PUT, API_ROUTE + "/payment/pay", new PaymentPay(), "", ""),
			new Route(Route.DELETE, API_ROUTE + "/payment/cancel", new PaymentCancel(), "", "")
		};
	
	@Override
	public Route[] getRoutes() {
		return ROUTES;
	}
}
