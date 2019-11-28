package com.test.di06;

public class SQLservice {
		private Dao dao;
		
		
		
		public SQLservice() {}
		
		
		
		public SQLservice(Dao dao) {
			this.dao = dao;
		}

		public Dao getDao() {
			return dao;
		}

		public void setDao(Dao dao) {
			this.dao = dao;
		}
		
		public void biz() {
			dao.add();
		}
}
