/**
 * 
 */
package com.depth1.grc.model;

import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;

/**
 * @author badedokun
 *
 */
@Accessor
public interface UserAccessor {
	/*@Query("SELECT * FROM grc.userprofile WHERE id = :id")
    UserProfile getUserNamed(@Param("userId") UUID id);*/

    @Query("SELECT * FROM grc.userprofile WHERE id = ?")
    UserProfile getOnePosition(UUID userId);

    //@Query("UPDATE grc.userprofile SET addresses[:name]=:address WHERE id = :id")
    //@Query("UPDATE grc.userprofile SET addresses=:address WHERE id = :id")
    //ResultSet addAddress(@Param("id") UUID id, @Param("name") String addressName, @Param("address") Address address);
    //public ResultSet addAddress(@Param("id") UUID id, @Param("address") Address address);
    
    
   @Query("SELECT * FROM grc.userprofile")
    public Result<UserProfile> getAll();

   /* @Query("SELECT * FROM grc.userprofile")
    public ListenableFuture<Result<UserProfile>> getAllAsync();*/

}
