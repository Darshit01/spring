package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rewards.RewardNetwork;
import rewards.internal.RewardNetworkImpl;
import rewards.internal.account.AccountRepository;
import rewards.internal.account.JdbcAccountRepository;
import rewards.internal.restaurant.JdbcRestaurantRepository;
import rewards.internal.restaurant.RestaurantRepository;
import rewards.internal.reward.JdbcRewardRepository;
import rewards.internal.reward.RewardRepository;

import javax.sql.DataSource;

/**
 * TODO-04: Implement each @Bean method to contain the code
 *          needed to instantiate its object and set its
 *          dependencies
 * - You can create beans from the following implementation classes
 *   - rewardNetwork bean from RewardNetworkImpl class
 *   - accountRepository bean from JdbcAccountRepository class
 *   - restaurantRepository bean from JdbcRestaurantRepository class
 *   - rewardRepository bean from JdbcRewardRepository class
 * - Note that return type of each bean method should be an interface
 *   not an implementation.
 */

@Configuration
public class RewardsConfig {

	private DataSource dataSource;

	public RewardsConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Bean
	public RewardNetwork rewardNetwork() {

        return new RewardNetworkImpl(accountRepository(), restaurantRepository(), rewardRepository());
    }

	@Bean
	public AccountRepository accountRepository(){

		 JdbcAccountRepository jdbcAccountRepository = new JdbcAccountRepository();
		 jdbcAccountRepository.setDataSource(dataSource);
		 return jdbcAccountRepository;
	}

	@Bean
	public RestaurantRepository restaurantRepository() {

		JdbcRestaurantRepository jdbcRestaurantRepository = new JdbcRestaurantRepository();
		jdbcRestaurantRepository.setDataSource(dataSource);
		return jdbcRestaurantRepository;
	}

	@Bean
	public RewardRepository rewardRepository() {

		JdbcRewardRepository jdbcRewardRepository = new JdbcRewardRepository();
		jdbcRewardRepository.setDataSource(dataSource);
		return jdbcRewardRepository;
	}

}
