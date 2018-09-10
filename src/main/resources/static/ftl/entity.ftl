package ${packagePath};

import java.io.Serializable;
import java.util.Date;



/**      
 * @date:       
 */
public class ${entity.name} implements Serializable {
    private static final long serialVersionUID = 1L;


<#list entity.attributes as attribute>
    //${attribute.remark}
    private ${attribute.type} ${attribute.name};
</#list>


<#list entity.attributes as attribute>
    public ${attribute.type} get${attribute.gName}(){
        return ${attribute.name};
    }
    public void set${attribute.sName} (${attribute.type} ${attribute.name}){
        this.${attribute.name} = ${attribute.name};
    }
</#list>
    
}