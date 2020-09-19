package com.zhb.music.dao;


import com.zhb.music.domain.Rank;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RankMapper {



    @Insert("insert into rank (id,songListId,consumerId,score) values(#{id},#{songListId},#{consumerId},#{score})")
    int insertSelective(Rank record);

    /**
     * 查总分
     * @param songListId
     * @return
     */
    @Select("select coalesce(sum(score),0) as score from rank where songListId = #{songListId}")
    int selectScoreSum(Long songListId);

    /**
     * 查总评分人数
     * @param songListId
     * @return
     */
    @Select("select count(*) from rank where songListId=#{songListId}")
    int selectRankNum(Long songListId);
}
