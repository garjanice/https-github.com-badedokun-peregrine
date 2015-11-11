/**
 * 
 */
package com.depth1.grc.security;

import javax.inject.Inject;

import play.api.mvc.EssentialFilter;
import play.filters.csrf.CSRFFilter;
import play.http.HttpFilters;

/**
 * @author badedokun
 *
 */
public class Filters implements HttpFilters {
	
	@Inject CSRFFilter csrfFilter;

    @Override
    public EssentialFilter[] filters() {
        return new EssentialFilter[] { csrfFilter };
    }

}
