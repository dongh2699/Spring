package com.sist.model;

import java.util.HashMap;
import java.util.List;

public interface BlogMemberDAO {
   // 가입된 블로그 보여주기
   public List<HashMap> blogMemberList(int b_no);

   public List<HashMap> searchMember(HashMap<String, Object> ngm);

   public int changeManage(BlogMemberDTO dto);
}