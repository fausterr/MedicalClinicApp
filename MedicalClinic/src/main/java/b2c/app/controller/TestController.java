package b2c.app.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.lowagie.text.DocumentException;
import b2c.app.model.Test;
import b2c.app.model.User;
import b2c.app.pdf.TestPdfExport;
import b2c.app.service.TestService;
import b2c.app.service.UserService;

@Controller
public class TestController {

	private TestService testService;
	private UserService userService;
	
	public TestController(TestService testService, UserService userService) {
		this.testService = testService;
		this.userService = userService;
	}
	
	@GetMapping("/tests")
	public void exportToPdf(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        
        User user = userService.getUserByLogin(UserService.getCurrentUser());
        List<Test> tests = testService.getAllByDate(user);
        TestPdfExport exporter = new TestPdfExport(tests);
        exporter.export(response);
	}
}