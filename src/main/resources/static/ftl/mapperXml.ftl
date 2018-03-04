<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperPackagePath}.${entity.name}Mapper">

  <!--BaseResultMap-->
  <resultMap id="BaseResultMap" type="${modelPackagePath}.${entity.name}">
    <#list entity.attributes as attribute>
      <#if attribute_index=0>
        <id column="${attribute.column}" jdbcType="${attribute.dbType}" property="${attribute.name}" />
      </#if>
      <#if attribute_index !=0>
        <result column="${attribute.column}" jdbcType="${attribute.dbType}" property="${attribute.name}" />
      </#if>
    </#list>
  </resultMap>
  
  <!--columns-->
  <sql id="sql_columns">
    <#list entity.attributes as attribute>
      ${attribute.column}<#if attribute_has_next>, </#if>
    </#list>
  </sql>
  
  <!--where-->
  <sql id="sql_where">
    <where>
       <#list entity.attributes as attribute>
      <if test="null != ${attribute.name} and '' != ${attribute.name}">and ${attribute.column} = ${"#{"+attribute.name+"}"}</if>
      </#list>
	</where>
  </sql>
  
  <select id="selectById" resultMap="BaseResultMap">
    select <include refid="sql_columns" /> from ${entity.table} where ${entity.attributes[0].column} = ${"#{"+entity.attributes[0].name+"}"}
  </select>
  
  <select id="selectOne" resultMap="BaseResultMap">
    select <include refid="sql_columns" /> from ${entity.table} <include refid="sql_where" />
  </select>
  
  <select id="selectList" resultMap="BaseResultMap">
    select <include refid="sql_columns" /> from ${entity.table} <include refid="sql_where" />
  </select>
  
  <select id="selectPage" resultMap="BaseResultMap">
    select <include refid="sql_columns" /> from ${entity.table} <include refid="sql_where" /> limit ${"#"+"{page.startRow}"},${"#"+"{page.pageSize}"}
  </select>
  
  <sql id="sql_save_columns">
    insert into ${entity.table}(
      <#list entity.attributes as attribute>
      <#if attribute_index!=0>
      <if test="null != ${attribute.name}">${attribute.column}</if>
      </#if>
      </#list>
	) values
  </sql>
  
  <sql id="sql_save_values">
    (
  <#list entity.attributes as attribute>
    <#if attribute_index != 0>
     <if test="null != ${attribute.name}"> ${"#{"+attribute.name+"}"}</if>
    </#if>
  </#list>
	)
  </sql>
  
  <insert id="save" keyProperty="${entity.attributes[0].column}" useGeneratedKeys="true">
    <include refid="sql_save_columns" /><include refid="sql_save_values" />
  </insert>
  
  <insert id="batchSave">
    <foreach collection="list" index="index" item="item" open="" separator=";" close="">
	  <include refid="sql_save_columns" /><include refid="sql_save_values" />
	</foreach>
  </insert>
  
  <sql id="sql_update">
    update ${entity.table} set ${entity.attributes[0].column} = ${"#{"+entity.attributes[0].name+"}"}
    <#list entity.attributes as attribute>
    <#if attribute_index !=0>
     <if test="null != ${attribute.name}">, ${attribute.column} = ${"#{"+attribute.name+"}"}</if>
    </#if>
   </#list>
	where ${entity.attributes[0].column} = ${"#{"+entity.attributes[0].name+"}"}
  </sql>
  
  <update id="update">
    <include refid="sql_update" />
  </update>
  
  <update id="batchUpdate">
    <foreach collection="list" index="index" item="item" open="" separator=";" close="">
	  <include refid="sql_update" />
	</foreach>
  </update>
  
  <delete id="delArray">
    delete from ${entity.table} where ${entity.attributes[0].column} in
	<foreach collection="array" index="index" item="item" open="(" separator="," close=")">${"#"+"{item}"}</foreach>
  </delete>
  
  <delete id="delList">
    delete from ${entity.table} where ${entity.attributes[0].column} in
	<foreach collection="list" index="index" item="item" open="(" separator="," close=")">${"#"+"{item}"}</foreach>
  </delete>
  
</mapper>