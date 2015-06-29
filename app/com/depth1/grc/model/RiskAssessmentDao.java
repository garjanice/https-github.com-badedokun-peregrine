package assessment;

public interface riskAssessmentDao {

	int createRiskAssessment();
	boolean updateRiskAssessment();
	boolean deleteRiskAssessment();
	void viewRiskAssessment();
	riskAssessment findRiskAssessment();
	void findAll(); //not type void, some list of riskAssessments
}
