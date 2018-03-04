
package ${serviceImplPackagePath};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${servicePackagePath}.${serviceImplName}Service;
import ${serviceImplPackagePath}.${serviceImplName}ServiceImpl;
import ${serviceImplPackagePath}.BaseServiceImpl;
import ${mapperPackagePath}.${serviceImplName}Mapper;
import ${modelPackagePath}.${serviceImplName};


@Service("${middleName}Service")
public class ${serviceImplName}ServiceImpl extends BaseServiceImpl<${serviceImplName}> implements ${serviceImplName}Service {

    @Autowired
	private ${serviceImplName}Mapper ${middleName}Mapper;
	
	@Autowired
	public void setMapper() {
		super.setMapper(${middleName}Mapper);
	}
	
}
