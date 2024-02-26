package com.paymentGateway.paytm.PaymentGateway.Controller;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
public class PaymentController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @PostMapping("/pay")
    @ResponseBody
    public String getPaymentData(@RequestBody Map<String, Object> data) throws RazorpayException, JSONException {

       int amt = Integer.parseInt(data.get("amount").toString());

        RazorpayClient razorpayClient = new RazorpayClient("rzp_test_Jn731apqnUTKB4", "1t7yMZD6viXcutIuJKE88lp0");

        JSONObject options = new JSONObject();

        options.put("amount", amt*100);
        options.put("currency", "INR");
        options.put("receipt", "txn_123456");

        Order order = razorpayClient.orders.create(options);

        System.out.println("order: "+order);

        return order.toString();
    }
}
