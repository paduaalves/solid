package br.com.alura.rh.model;

public enum Cargo {

	ASSISTENTE {
		@Override
		public Cargo getProximoCargo() {
			// TODO Auto-generated method stub
			return ANALISTA;
		}
	},
	ANALISTA {
		@Override
		public Cargo getProximoCargo() {
			// TODO Auto-generated method stub
			return ESPECIALISTA;
		}
	},
	ESPECIALISTA {
		@Override
		public Cargo getProximoCargo() {
			// TODO Auto-generated method stub
			return GERENTE;
		}
	},
	GERENTE {
		@Override
		public Cargo getProximoCargo() {
			// TODO Auto-generated method stub
			return GERENTE;
		}
	};

	public abstract Cargo getProximoCargo();
		

}
