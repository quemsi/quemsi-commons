package com.quemsi.commons.persistence;

public class Views {
	public interface OnlyIdName {
		
	}
	public interface BasicInfo extends OnlyIdName{
		
	}
	public interface WithRelations extends BasicInfo{
		
	}
	public interface ExtraInfo extends WithRelations{
		
	}
	public interface WithAudit extends ExtraInfo{
		
	}
	public interface FkBase {
	}
	public class Timer {
		public interface WithFk extends FkBase{
		}
	}
	public class Datasource {
		public interface WithFk extends FkBase{
		}
	}
	public class LocalDrive {
		public interface WithFk extends FkBase {
		}
	}
	public class GoogleDrive {
		public interface WithFk extends FkBase {
		}
	}
	public class Storage {
		public interface WithFk extends FkBase {
		}
	}

	public class AgentError {
		public interface WithFk extends FkBase{
		}
	}
	
	public class AgentCrud{
		public interface WithFk extends FkBase{
		}
		public interface WithExtra extends WithFk {
		}
	}
	public interface AllWithFk extends Timer.WithFk, Datasource.WithFk, LocalDrive.WithFk, GoogleDrive.WithFk, Storage.WithFk, AgentError.WithFk, Agent.WithFk {
	}

	public class Agent {
		public interface WithFk extends FkBase{
		}
		public interface AgentModel extends BasicInfo {
		}
	}
}
