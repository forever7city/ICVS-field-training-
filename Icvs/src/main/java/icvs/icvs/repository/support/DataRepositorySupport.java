package icvs.icvs.repository.support;

public abstract class DataRepositorySupport extends QuerydslRepositorySupport {
	public DataRepositorySupport(Class<?> domainClass) {
		super(domainClass);
	}
}