package com.depth1.grc.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EnumConstant {
	public enum ObjectiveType {
		STRATEGIC, OPERATIONS, EXTERNAL_FINANCIAL_REPORTING, EXTERNAL_NON_FINANCIAL_REPORTING, INTERNAL_REPORTING, COMPLIANCE
	}

	public enum Level {
		ENTITY, DIVISION, BUSINESS_UNIT, SUBSIDIARY, OPERATING_UNIT, FUNCTION
	}
	
	public enum Oversight {
		GOVERNING_BODY, BOARD, SENIOR_MANAGEMENT, AUDIT_COMMITTEE
	}

	private String description;
	private ObjectiveType objectiveType;

	public EnumConstant(String description, ObjectiveType objectiveType) {
		this.description = description;
		this.objectiveType = objectiveType;
	}
	
	/**
	 * Returns a map of objective types with objectives
	 * @param objective array of EnumConstant objects providing information such as objective description
	 * @return
	 */

	public Map<EnumConstant.ObjectiveType, Set<EnumConstant>> objective(EnumConstant[] objective) {
		Map<EnumConstant.ObjectiveType, Set<EnumConstant>> objectiveByType = new EnumMap<EnumConstant.ObjectiveType, Set<EnumConstant>>(EnumConstant.ObjectiveType.class);
		for (EnumConstant.ObjectiveType t : EnumConstant.ObjectiveType.values())
			objectiveByType.put(t, new HashSet<EnumConstant>());
		for (EnumConstant o : objective)
			objectiveByType.get(o.objectiveType).add(o);
		
		return objectiveByType;
	}
	
	/**
	 * Returns a list of objective types.
	 * 
	 * @return list of objective types in the enum.
	 */
	public List<EnumConstant.ObjectiveType> listObjective() {
		return new ArrayList<EnumConstant.ObjectiveType>(Arrays.asList(EnumConstant.ObjectiveType.values()));
	}
	
	/**
	 * Returns a list of entity types.
	 * 
	 * @return list of entity types in the enum.
	 */
	public List<EnumConstant.Level> listEntityLevel() {
		return new ArrayList<EnumConstant.Level>(Arrays.asList(EnumConstant.Level.values()));
	}
	
	/**
	 * Returns a list of oversight body.
	 * 
	 * @return list of oversight members in the enum.
	 */
	public List<EnumConstant.Oversight> listOversight() {
		return new ArrayList<EnumConstant.Oversight>(Arrays.asList(EnumConstant.Oversight.values()));
	}	

	/**
	 * Override of toString method
	 */
	@Override public String toString() {
		return description;
	}

}
