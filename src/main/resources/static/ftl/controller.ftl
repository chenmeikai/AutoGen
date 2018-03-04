
package ${controllerPackagePath};

import ${servicePackagePath}.${controllerName}Service;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;

@Controller
public class ${controllerName}Controller {
	
	@Resource
	private ${controllerName}Service ${middleName}Service;
	
}
