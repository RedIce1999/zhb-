package com.zhb.music.service;


import com.zhb.music.domain.Rank;

public interface RankService {

    int rankOfSongListId(Long songListId);

    boolean addRank(Rank rank);
}
