package net.renfei.repositories;

import java.util.List;
import net.renfei.repositories.model.SysVerificationCode;
import net.renfei.repositories.model.SysVerificationCodeExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysVerificationCodeMapper {
    long countByExample(SysVerificationCodeExample example);

    int deleteByExample(SysVerificationCodeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysVerificationCode record);

    int insertSelective(SysVerificationCode record);

    List<SysVerificationCode> selectByExample(SysVerificationCodeExample example);

    SysVerificationCode selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysVerificationCode record, @Param("example") SysVerificationCodeExample example);

    int updateByExample(@Param("record") SysVerificationCode record, @Param("example") SysVerificationCodeExample example);

    int updateByPrimaryKeySelective(SysVerificationCode record);

    int updateByPrimaryKey(SysVerificationCode record);
}