package org.hyperledger.indy.sdk.pool;

import org.hyperledger.indy.sdk.ErrorCode;
import org.hyperledger.indy.sdk.ErrorCodeMatcher;
import org.hyperledger.indy.sdk.IndyIntegrationTest;
import org.hyperledger.indy.sdk.utils.PoolUtils;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DeletePoolTest extends IndyIntegrationTest {

	@Test
	public void testDeletePoolWorks() throws Exception {
		String poolName = PoolUtils.createPoolLedgerConfig();
		Pool.deletePoolLedgerConfig(poolName).get();
	}

	@Test
	public void testDeletePoolWorksForOpened() throws Exception {
		thrown.expectCause(new ErrorCodeMatcher(ErrorCode.CommonInvalidState));

		String poolName = PoolUtils.createPoolLedgerConfig();
		Pool pool = Pool.openPoolLedger(poolName, null).get();
		assertNotNull(pool);
		openedPools.add(pool);
		Pool.deletePoolLedgerConfig(poolName).get();
	}
}
