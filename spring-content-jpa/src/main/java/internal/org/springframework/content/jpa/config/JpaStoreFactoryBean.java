package internal.org.springframework.content.jpa.config;

import internal.org.springframework.content.jpa.io.DelegatingBlobResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.content.commons.repository.factory.AbstractStoreFactoryBean;
import org.springframework.util.Assert;

import internal.org.springframework.content.jpa.operations.JpaContentTemplate;
import internal.org.springframework.content.jpa.repository.DefaultJpaStoreImpl;

import javax.sql.DataSource;

@SuppressWarnings("rawtypes")
public class JpaStoreFactoryBean extends AbstractStoreFactoryBean {

	@Autowired 
	private JpaContentTemplate template;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private DelegatingBlobResourceLoader blobResourceLoader;

	@Autowired
	private JpaStorePropertiesImpl properties;

	@Override
	protected Object getContentStoreImpl() {
		Assert.notNull(template, "template cannot be null");
		Assert.notNull(blobResourceLoader, "blobResourceLoader cannot be null");
		return new DefaultJpaStoreImpl(blobResourceLoader, properties.getCommitTimeout());
	}

}