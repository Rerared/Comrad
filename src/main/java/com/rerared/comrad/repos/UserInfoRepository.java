package com.rerared.comrad.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rerared.comrad.domain.UserInfo;
@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, Integer> {
	List<UserInfo> findBySirname(String sirname);

}
