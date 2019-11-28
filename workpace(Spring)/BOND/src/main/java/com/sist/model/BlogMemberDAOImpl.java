package com.sist.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BlogMemberDAOImpl implements BlogMemberDAO {

   @Autowired
   private SqlSessionTemplate sqlSession;

   @Override
   public List<HashMap> blogMemberList(int b_no) {
      return this.sqlSession.selectList("get_BlogMember", b_no);
   }

   @Override
   public List<HashMap> searchMember(HashMap<String, Object> ngm) {
      List<HashMap> test = null;
      if (this.sqlSession.selectList("get_SearchMember", ngm) == null) {
         return test;
      }
      return this.sqlSession.selectList("get_SearchMember", ngm);
   }

   @Override
   public int changeManage(BlogMemberDTO dto) {
      return this.sqlSession.update("changeManage", dto);
   }

}