package ru.fella;

import ru.fella.jdbc.DAO;
import ru.fella.jms.JMSService;
import ru.fella.type.AccountBalanceRequest;


import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;


@WebServlet(urlPatterns = "/servlet")
public class AccountServlet extends HttpServlet {

    @Inject
    JMSService jmsService;

    @Inject
    DAO dao;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.append("We started processing your request" + System.lineSeparator());

        AccountBalanceRequest accountBalanceRequest = getObjectFromRequest(req);
        String accountBalanceInXML = getXmlFromObject(accountBalanceRequest);

        writer.append(accountBalanceInXML);

        jmsService.send(accountBalanceInXML);

        dao.addNewEntry();
        writer.append( System.lineSeparator() + "End processing your request");

    }


    public  AccountBalanceRequest getObjectFromRequest(HttpServletRequest req){
        AccountBalanceRequest accountBalanceRequest = new AccountBalanceRequest();
        accountBalanceRequest.setClientName(req.getParameter("name"));
        accountBalanceRequest.setClientSurname(req.getParameter("surname"));
        accountBalanceRequest.setAccountBalance(new BigDecimal(req.getParameter("balance")));
        accountBalanceRequest.setAccountNumber(Long.valueOf(req.getParameter("account")));
        return accountBalanceRequest;
    }

    public String getXmlFromObject(AccountBalanceRequest accountBalanceRequest){
        StringWriter sw = new StringWriter();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(AccountBalanceRequest.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(accountBalanceRequest,sw );
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sw.toString();
        }

}
