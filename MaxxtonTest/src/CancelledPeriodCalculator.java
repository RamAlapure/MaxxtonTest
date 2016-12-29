import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CancelledPeriodCalculator {

	public static void main(String[] args) throws ParseException {
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		
		List<Plan> oldPlanList = new ArrayList<>();
		for (long j = 101; j < 108; j++) {
			oldPlanList.add(new Plan(j, format.parse("10-05-2016"), format.parse("20-05-2016")));
		}
		
		List<Plan> newPlanList = new ArrayList<>();
		
		newPlanList.add(new Plan(101L, format.parse("01-05-2016"), format.parse("05-05-2016")));
		newPlanList.add(new Plan(102L, format.parse("05-05-2016"), format.parse("15-05-2016")));
		newPlanList.add(new Plan(103L, format.parse("05-05-2016"), format.parse("25-05-2016")));
		newPlanList.add(new Plan(104L, format.parse("15-05-2016"), format.parse("18-05-2016")));
		newPlanList.add(new Plan(105L, format.parse("15-05-2016"), format.parse("25-05-2016")));
		newPlanList.add(new Plan(106L, format.parse("25-05-2016"), format.parse("30-05-2016")));
		newPlanList.add(new Plan(107L, format.parse("12-05-2016"), format.parse("13-05-2016")));
		newPlanList.add(new Plan(107L, format.parse("16-05-2016"), format.parse("18-05-2016")));

		List<Plan> cancelledPlanList = getCancelledPeriodPlanList(oldPlanList, newPlanList);
		
		cancelledPlanList.forEach(plan ->{
			System.out.println(plan);
		});
	}

	private static List<Plan> getCancelledPeriodPlanList(List<Plan> oldPlanList, List<Plan> newPlanList) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		
		List<Plan> cancelledPlanList = new ArrayList<>();
		
		oldPlanList.forEach(oldPlan ->{
			Iterator<Plan> itr = newPlanList.iterator();
			while(itr.hasNext()){
				Plan newPlan = itr.next();
				if(oldPlan.getId().equals(newPlan.getId())){
					if(newPlan.getStartDate().before(oldPlan.getStartDate())){
						if(newPlan.getEndDate().before(oldPlan.getStartDate())){
							cancelledPlanList.add(new Plan(oldPlan.getId(), oldPlan.getStartDate(), oldPlan.getEndDate()));
						}else{
							if(newPlan.getEndDate().before(oldPlan.getEndDate())){
								Date date = newPlan.getEndDate();
								cal.setTime(date);
								cal.add(Calendar.DATE, 1);
								cancelledPlanList.add(new Plan(oldPlan.getId(), cal.getTime(), oldPlan.getEndDate()));
							}
						}
					}else{
						if(newPlan.getStartDate().before(oldPlan.getEndDate())){
							if(newPlan.getEndDate().before(oldPlan.getEndDate())){
								int index = cancelledPlanList.size()-1;
								Plan cancelledPlan = cancelledPlanList.get(index);
								Date date = newPlan.getStartDate();
								cal.setTime(date);
								cal.add(Calendar.DATE, -1);
								if(cancelledPlan.getId().equals(newPlan.getId())){
									cancelledPlanList.set(index, new Plan(cancelledPlan.getId(), cancelledPlan.getStartDate(), cal.getTime()));
								}else{
									cancelledPlanList.add(new Plan(oldPlan.getId(), oldPlan.getStartDate(), cal.getTime()));
								}
							
								date = newPlan.getEndDate();
								cal.setTime(date);
								cal.add(Calendar.DATE, 1);
								cancelledPlanList.add(new Plan(oldPlan.getId(), cal.getTime(), oldPlan.getEndDate()));
							}else{
								Date date = newPlan.getStartDate();
								cal.setTime(date);
								cal.add(Calendar.DATE, -1);
								cancelledPlanList.add(new Plan(oldPlan.getId(), oldPlan.getStartDate(), cal.getTime()));
							}
						}else{
							cancelledPlanList.add(new Plan(oldPlan.getId(), oldPlan.getStartDate(), oldPlan.getEndDate()));
						}
					}
				}
			}
		});
		
		return cancelledPlanList;
	}

}
