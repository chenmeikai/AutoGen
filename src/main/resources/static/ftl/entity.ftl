package ${packagePath};

import java.io.Serializable;



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
    public ${attribute.type} get${attribute.name}(){
        return ${attribute.name};
    }
    public void set${attribute.name} (${attribute.type} ${attribute.name}s){
        this.${attribute.name} = ${attribute.name};
    }
    </#list>
    
}