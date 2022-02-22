package net.renfei.repositories;

import java.util.List;
import java.util.Map;

import net.renfei.repositories.model.DocsOnlineDocuments;
import net.renfei.repositories.model.DocsOnlineDocumentsExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DocsOnlineDocumentsMapper {
    long countByExample(DocsOnlineDocumentsExample example);

    int deleteByExample(DocsOnlineDocumentsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DocsOnlineDocuments record);

    int insertSelective(DocsOnlineDocuments record);

    List<DocsOnlineDocuments> selectByExample(DocsOnlineDocumentsExample example);

    DocsOnlineDocuments selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DocsOnlineDocuments record, @Param("example") DocsOnlineDocumentsExample example);

    int updateByExample(@Param("record") DocsOnlineDocuments record, @Param("example") DocsOnlineDocumentsExample example);

    int updateByPrimaryKeySelective(DocsOnlineDocuments record);

    int updateByPrimaryKey(DocsOnlineDocuments record);

    List<Map<String,Object>> selectCategory();
}