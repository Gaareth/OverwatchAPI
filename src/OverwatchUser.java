package api;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import Exceptions.NoStatsAvailableException;
import Exceptions.NotAUserException;

public class OverwatchUser {
	
	//GENERAL
    private String name, lvl, favHero, winRate, timePlayed, skillRating, heroImage, userImage;
    //FEATURED
    private String eliminations_KD, eliminations_kad, eliminations_Average;
    //PERFOMANCE
    private String damage_Average, healing_Average;
    //COMBAT
    private String soloKills_Total, eliminations_Total, damage_Total, objectiveKills_Total, finalBlows_Total, enviromentalKills_Total, multiKills_Total;
    //DEATHS
    private String enviromentalDeaths_Total, deaths_Total, deaths_Average;
    //GAMES
    private String gamesWon, gamesPlayed, objectiveTime_Total, TimeOnFire_Total;
    //ASSIST
    private String healing_Total;
    //BEST
    private String eliminations_Most;
    private String finalBlows_Most;
    private String damage_Most;
    private String healing_Most;
    private String objectiveKills_Most;
    private String objectiveTime_Most;
    private String soloKills_Most;
    private String TimeOnFire_Most;
    //Medals
    private String goldMedal;
    private String silverMedal;
    private String bronzeMedal;
    private String amountMedals;
    private long time;
	private boolean competitive;
	
    
    
    
    
	private WebDriver driver = new HtmlUnitDriver();

	public long getTime() {
		return time;
	}

	/**
     * Initializes the Webdriver and OverwatchUser object
     * 
     * 	@param hero
     *         The specific hero you want the statistics from. Leave it empty when you dont want to get statistics for a hero
     *         
     *  @param competitive 
     *  	   Game mode for statistics as a boolean
     *  
     *  @param name
     *  	   The Overwatch user name
     *        
     *	@exception catches ElementNotFoundException
     *
     * 
     */
	public OverwatchUser(String name, boolean competitive, String hero) {

		this.competitive = competitive;
		long startTime = System.currentTimeMillis();
		
		if(competitive) {
			  if((hero.length() >= 1)) { driver.get("https://overwatchtracker.com/profile/pc/global/" + name + "/heroes/" + hero.toLowerCase() + "?mode=1"); System.out.println("comp hero" );}
			  else
				  driver.get("https://overwatchtracker.com/profile/pc/global/" + name + "?mode=1");

		}else {
			  
			if((hero.length() >= 1)) { driver.get("https://overwatchtracker.com/profile/pc/global/" + name + "/heroes/" + hero.toLowerCase() + "?mode=0"); System.out.println("qu8ck hero");}
			else
			driver.get("https://overwatchtracker.com/profile/pc/global/" + name + "?mode=0");
		}


		System.out.println("Url: " + driver.getCurrentUrl());
		
	        try {
	            WebElement error = driver.findElement(By.cssSelector(".container > h2"));

	            if(error.getText().equalsIgnoreCase("Error while searching!"))
	            {
	            	System.out.println("There is no user with that name!");
	            	throw new NotAUserException("There is no user with that name!");
	            }
	            
	            if(driver.findElement(By.cssSelector("div:nth-child(1) > div.alert.alert-danger")).getText().equalsIgnoreCase("No stats for this Hero for this Mode"))
	            {
	            	System.out.println("No stats for this Hero for this Mode!");
	            	throw new NoStatsAvailableException("No stats for this Hero for this Mode!");
	            }
	            
	            
	            
	        } catch(Exception exception) {
	        }

	        
	        if(competitive)
	        {
	        	
	        	if((hero.length() >= 1)) {
	        		try {
	        			if(driver.findElement(By.cssSelector("div:nth-child(1) > div.alert.alert-danger")).getText().equalsIgnoreCase("No stats for this Hero for this Mode"))
			            {
	    	            	System.out.println("No stats for this Hero for this Mode!");
	    	            	throw new NoStatsAvailableException("No stats for this Hero for this Mode!");

			            }
					} catch (Exception e2) {
					}
		        	searchCompetitiveStatsHero(driver, hero);
		        }else {
		 	     
		 	   
		        //GENERAL
	        	//WebElement userName = driver.findElement(By.cssSelector(".player-info > .name"));
	 	        List<WebElement> infoBoxContainer = driver.findElements(By.cssSelector(".infobox-container > .infobox"));
	 	        this.skillRating = infoBoxContainer.get(0).findElement(By.cssSelector(".value")).getText();
	 	        this.lvl = infoBoxContainer.get(1).findElement(By.cssSelector(".value")).getText();
	 	        this.timePlayed = infoBoxContainer.get(3).findElement(By.cssSelector(".value")).getText();
	 	        this.favHero = infoBoxContainer.get(4).findElement(By.cssSelector(".value")).getText();
	 	        
	 	       
	 	        
	 	        List<WebElement> links=driver.findElements(By.tagName("img"));
	 	        this.userImage = links.get(12).getAttribute("src");
	 	       
	 	        //FEATURED
	 	        List<WebElement> containerFeatured = driver.findElements(By.cssSelector(".aside > .material-card > .stats-large > .stat"));
	 	        this.eliminations_KD = containerFeatured.get(2).findElement(By.cssSelector(".value")).getText();
	 	        this.eliminations_kad = containerFeatured.get(3).findElement(By.cssSelector(".value")).getText();
	 	        this.eliminations_Average = containerFeatured.get(5).findElement(By.cssSelector(".value")).getText();

	 	        List<WebElement> containerPerformance = driver.findElements(By.cssSelector(".aside > .material-card > .stats-large > .stat"));
	 	        this.damage_Average = containerPerformance.get(7).findElement(By.cssSelector(".value")).getText();
	 	        this.healing_Average = containerPerformance.get(8).findElement(By.cssSelector(".value")).getText();
	 	       
	 	        //COMBAT
	 	        List<WebElement> containerCombat = driver.findElements(By.cssSelector(".aside > .material-card > .stats-large > .stat"));
	 	        this.soloKills_Total = containerCombat.get(12).findElement(By.cssSelector(".value")).getText();
	 	        this.objectiveKills_Total = containerCombat.get(13).findElement(By.cssSelector(".value")).getText();
	 	        this.finalBlows_Total = containerCombat.get(14).findElement(By.cssSelector(".value")).getText();
	 	        this.damage_Total = containerCombat.get(15).findElement(By.cssSelector(".value")).getText();
	 	        this.eliminations_Total = containerCombat.get(16).findElement(By.cssSelector(".value")).getText();
	 	        this.enviromentalKills_Total = containerCombat.get(17).findElement(By.cssSelector(".value")).getText();
	 	        
	 	        //DEATHS
	 	        List<WebElement> containerDeaths = driver.findElements(By.cssSelector(".aside > .material-card > .stats-large > .stat"));
	 	        this.enviromentalDeaths_Total = containerDeaths.get(20).findElement(By.cssSelector(".value")).getText();	 	        
	 	        
	 	        //GAMES
	 	        List<WebElement> containerGames = driver.findElements(By.cssSelector(".aside > .material-card > .stats-large > .stat"));
	 	        this.gamesWon = containerGames.get(21).findElement(By.cssSelector(".value")).getText();
	 	        this.gamesPlayed = containerGames.get(22).findElement(By.cssSelector(".value")).getText();
	 	        this.TimeOnFire_Total = containerGames.get(23).findElement(By.cssSelector(".value")).getText();
	 	        this.objectiveTime_Total = containerGames.get(24).findElement(By.cssSelector(".value")).getText();
	 	        
	 	        //ASSIST 
	 	        List<WebElement> containerAssist = driver.findElements(By.cssSelector(".aside > .material-card > .stats-large > .stat"));
	 	        this.healing_Total = containerAssist.get(27).findElement(By.cssSelector(".value")).getText();
	 	       	 	        
	 	        //BEST
	 	        List<WebElement> containerBest = driver.findElements(By.cssSelector(".aside > .material-card > .stats-large > .stat"));
	 	        this.finalBlows_Most = containerBest.get(31).findElement(By.cssSelector(".value")).getText();
	 	        this.damage_Most = containerBest.get(32).findElement(By.cssSelector(".value")).getText();
	 	        this.healing_Most = containerBest.get(33).findElement(By.cssSelector(".value")).getText();
	 	        this.objectiveKills_Most = containerBest.get(36).findElement(By.cssSelector(".value")).getText();
	 	        this.objectiveTime_Most = containerBest.get(37).findElement(By.cssSelector(".value")).getText();
	 	        this.soloKills_Most = containerBest.get(38).findElement(By.cssSelector(".value")).getText();
	 	        this.TimeOnFire_Most = containerBest.get(39).findElement(By.cssSelector(".value")).getText();
	 	        long endTime = System.currentTimeMillis();
	 	        
	 	        time = TimeUnit.MILLISECONDS.toMicros(endTime - startTime);
	 	        
		        }
		        
	        	
	        }else if(!competitive) {
	        
	        	try {
	        	if(hero.length() > 1) {
	        		searchQuickPlayStatsHero(driver, hero);
	        	}else {
	        		searchQuickPlayStatsNormal(driver, startTime);
	        	}
	        	}catch(Exception exception) {
	        		exception.printStackTrace();
	        	}
	        	
		        
	        }
	       

	        		
	}
	
	public void debug()
	{
	     	
	        
 	        System.out.println("KD " + eliminations_KD);
 	        
 	        System.out.println("Most Time spent on FIRE: " + TimeOnFire_Most);
 	       	System.out.println("Most solo Kills: " + soloKills_Most);
 	       	System.out.println("Most objective Time: " + objectiveTime_Most);
 	       	System.out.println("Most objective Kills: " + objectiveKills_Most);
 	       	System.out.println("Most healing: " + healing_Most);
 	       	System.out.println("Most damage: " + damage_Most);
 	       	System.out.println("Most final blow: " + finalBlows_Most);
 	       	System.out.println("Most eliminations: " + eliminations_Most);
 	       	System.out.println("Total healing: " + healing_Total);
 	       	System.out.println("Total objective Time: " + objectiveTime_Total);
 	       	System.out.println("Time spent on FIRE: " + TimeOnFire_Total);
 	       	System.out.println("games played: " + gamesPlayed);
 	       	System.out.println("games won: " + gamesWon);
 	       	System.out.println("Total environmental kills: " + enviromentalDeaths_Total);
 	       	System.out.println("Total environmental kills: " + enviromentalKills_Total);
 	       	System.out.println("Total Kills: " + eliminations_Total);
 	       	System.out.println("Total Damage: " + damage_Total);
 	        System.out.println("Total final blows: " + finalBlows_Total);
 	        System.out.println("Total Solo Kills: " + soloKills_Total);
 	        System.out.println("Average Healing: " + healing_Average);
 	        System.out.println("Average Damage: " + damage_Average);
 	      	System.out.println("Average Kills: " + eliminations_Average);
 	        System.out.println("KDA: " + eliminations_kad);
 	        System.out.println("Total Objective Kills: " + objectiveKills_Total);


	}


	/**
     * Searches for quickplay statistics for the user
     * 
     *  @param driver
     *         The selenium webdriver needed for scraping
     *         
     * 	@param startTime
     *         The startTime for the time calculation
     *        
     *	
     *
     * 
     */
	public void searchQuickPlayStatsNormal(WebDriver driver, long startTime) {
	        List<WebElement> infoBoxContainer = driver.findElements(By.cssSelector(".infobox-container > .infobox"));
	        this.skillRating = "N/A";
	        this.lvl = infoBoxContainer.get(1).findElement(By.cssSelector(".value")).getText();
	        this.timePlayed = infoBoxContainer.get(3).findElement(By.cssSelector(".value")).getText();
	        this.favHero = infoBoxContainer.get(4).findElement(By.cssSelector(".value")).getText();
	  
	        List<WebElement> links=driver.findElements(By.tagName("img"));
	        System.out.println("Image: " + links.get(12).getAttribute("src"));
	        this.userImage = links.get(12).getAttribute("src");
	       
	        //FEATURED
	        List<WebElement> containerFeatured = driver.findElements(By.cssSelector(".aside > .material-card > .stats-large > .stat"));
	        this.eliminations_KD = containerFeatured.get(1).findElement(By.cssSelector(".value")).getText();
	        System.out.println("KD " + eliminations_KD);
	        
	        this.eliminations_kad = containerFeatured.get(2).findElement(By.cssSelector(".value")).getText();
	        System.out.println("KDA: " + eliminations_kad);
	        
	        this.eliminations_Average = containerFeatured.get(4).findElement(By.cssSelector(".value")).getText();
	        System.out.println("Average Kills Per MIn: " + eliminations_Average);

	        List<WebElement> containerPerformance = driver.findElements(By.cssSelector(".aside > .material-card > .stats-large > .stat"));
	        this.damage_Average = containerPerformance.get(6).findElement(By.cssSelector(".value")).getText();
	        System.out.println("Average Damage Per Min: " + damage_Average);

	        this.healing_Average = containerPerformance.get(5).findElement(By.cssSelector(".value")).getText();
	        System.out.println("Average Healing Per Min: " + healing_Average);

	        //COMBAT
	        List<WebElement> containerCombat = driver.findElements(By.cssSelector(".aside > .material-card > .stats-large > .stat"));
	        this.soloKills_Total = containerCombat.get(7).findElement(By.cssSelector(".value")).getText();
	        System.out.println("Total Solo Kills: " + soloKills_Total);
	
	        this.objectiveKills_Total = containerCombat.get(8).findElement(By.cssSelector(".value")).getText();
	        System.out.println("Total Objective Kills: " + objectiveKills_Total);
	
	        this.finalBlows_Total = containerCombat.get(9).findElement(By.cssSelector(".value")).getText();
	        System.out.println("Total final blows: " + finalBlows_Total);
	
	        this.damage_Total = containerCombat.get(10).findElement(By.cssSelector(".value")).getText();
	        System.out.println("Total Damage: " + damage_Total);
	        
	        this.eliminations_Total = containerCombat.get(11).findElement(By.cssSelector(".value")).getText();
	        System.out.println("Total Kills: " + eliminations_Total);

	        this.enviromentalKills_Total = containerCombat.get(12).findElement(By.cssSelector(".value")).getText();
	        System.out.println("Total environmental kills: " + enviromentalKills_Total);
	        
	        this.multiKills_Total = containerCombat.get(13).findElement(By.cssSelector(".value")).getText();
	        System.out.println("Total Multi kills: " + enviromentalKills_Total);
	        
	        //DEATHS
	        
	        List<WebElement> containerDeaths = driver.findElements(By.cssSelector(".aside > .material-card > .stats-large > .stat"));
	        this.enviromentalDeaths_Total = containerDeaths.get(15).findElement(By.cssSelector(".value")).getText();
	        System.out.println("Total environmental deaths: " + enviromentalDeaths_Total);
	        
	        
	        //GAMES
	        
	        List<WebElement> containerGames = driver.findElements(By.cssSelector(".aside > .material-card > .stats-large > .stat"));
	        this.gamesWon = containerGames.get(16).findElement(By.cssSelector(".value")).getText();
	        System.out.println("games won: " + gamesWon);
	        
	        this.gamesPlayed = containerGames.get(17).findElement(By.cssSelector(".value")).getText();
	        System.out.println("games played: " + gamesPlayed);
	        
	        this.TimeOnFire_Total = containerGames.get(18).findElement(By.cssSelector(".value")).getText();
	        System.out.println("Time spent on FIRE: " + TimeOnFire_Total);
	        
	        this.objectiveTime_Total = containerGames.get(19).findElement(By.cssSelector(".value")).getText();
	        System.out.println("Total objective Time: " + objectiveTime_Total);
	        
	        
	        //ASSIST 
	        List<WebElement> containerAssist = driver.findElements(By.cssSelector(".aside > .material-card > .stats-large > .stat"));
	        this.healing_Total = containerAssist.get(22).findElement(By.cssSelector(".value")).getText();
	        System.out.println("Total healing: " + healing_Total);
	        
	        
	        
	        //BEST
	        
	        List<WebElement> containerBest = driver.findElements(By.cssSelector(".aside > .material-card > .stats-large > .stat"));
	        this.eliminations_Most = containerBest.get(25).findElement(By.cssSelector(".value")).getText();
	        System.out.println("Most eliminations: " + eliminations_Most);
	        
	        this.finalBlows_Most = containerBest.get(26).findElement(By.cssSelector(".value")).getText();
	        System.out.println("Most final blow: " + finalBlows_Most);
	        
	        this.damage_Most = containerBest.get(27).findElement(By.cssSelector(".value")).getText();
	        this.damage_Most += " " +containerBest.get(32).findElement(By.cssSelector(".rank")).getText();
	        System.out.println("Most damage: " + damage_Most);
	        
	        this.healing_Most = containerBest.get(28).findElement(By.cssSelector(".value")).getText();
	        System.out.println("Most healing: " + healing_Most);
	        
	        this.objectiveKills_Most = containerBest.get(31).findElement(By.cssSelector(".value")).getText();
	        System.out.println("Most objective Kills: " + objectiveKills_Most);
	        
	        this.objectiveTime_Most = containerBest.get(32).findElement(By.cssSelector(".value")).getText();
	        System.out.println("Most objective Time: " + objectiveTime_Most);
	        
	        this.soloKills_Most = containerBest.get(33).findElement(By.cssSelector(".value")).getText();
	        System.out.println("Most solo Kills: " + soloKills_Most);
	        
	        this.TimeOnFire_Most = containerBest.get(34).findElement(By.cssSelector(".value")).getText();
	        System.out.println("Most Time spent on FIRE: " + TimeOnFire_Most);
	
	        long endTime = System.currentTimeMillis();
	        
	        time = TimeUnit.MILLISECONDS.toMicros(endTime - startTime);
	        System.out.println("Time: " + (endTime - startTime));
	        
	        
	}
	
	/**
     * Searches for quickplay statistics for the specific hero on overwatchtracker.com
     * 
     *  @param driver
     *         The selenium webdriver needed for scraping
     *         
     * 	@param hero
     *         The specific hero you want the statistics from
     *        
     *	@exception catches ElementNotFoundException
     *
     */
	public void searchQuickPlayStatsHero(WebDriver driver, String hero) {
		
		try
		{
		System.out.println("Hero: " + hero);
    	
    	this.heroImage = driver.findElement(By.cssSelector("div.hero-icon.large > div")).getAttribute("style");
    	String array[] = driver.findElement(By.cssSelector("div.hero-icon.large > div")).getAttribute("style").split("\\(");
    	this.heroImage = array[1];
    	String array2[] = this.heroImage.split("\\)");
    	this.heroImage = array2[0].trim();
    	System.out.println("USerImAGE: "  + heroImage);
    	
    	this.eliminations_kad = driver.findElement(By.cssSelector("div:nth-child(2) > div.stats-large > div:nth-child(2) > div.value")).getText();
	        System.out.println("KAD: " + eliminations_kad);
    	
    	this.eliminations_KD = driver.findElement(By.cssSelector("div:nth-child(2) > div.stats-large > div:nth-child(1) > div.value")).getText();
	        System.out.println("KD: " + eliminations_KD);
	       
	        this.soloKills_Total = driver.findElement(By.cssSelector("div:nth-child(3) > div.stats-large > div:nth-child(1) > div.value")).getText();
	        System.out.println("SoloKills total: " + soloKills_Total);
	      
	        this.objectiveKills_Total = driver.findElement(By.cssSelector("div:nth-child(3) > div.stats-large > div:nth-child(2) > div.value")).getText();
	        System.out.println("ObjectiveKills total: " + objectiveKills_Total);
	        
	        this.finalBlows_Total = driver.findElement(By.cssSelector( "div:nth-child(3) > div.stats-large > div:nth-child(3) > div.value")).getText();
	        System.out.println("FinalBlows total: " + finalBlows_Total);
	        
	        this.eliminations_Total = driver.findElement(By.cssSelector( "div:nth-child(3) > div.stats-large > div:nth-child(4) > div.value")).getText();
	        System.out.println("Eliminations Total total: " + eliminations_Total);
	        
	        this.deaths_Total = driver.findElement(By.cssSelector( "div:nth-child(4) > div.stats-large > div > div.value")).getText();
	        System.out.println("Deaths Total: " + deaths_Total);
	        
	        this.gamesWon = driver.findElement(By.cssSelector("div:nth-child(5) > div.stats-large > div:nth-child(1) > div.value")).getText();
	        System.out.println("Games won: " + gamesWon);

//	        this.timePlayed = driver.findElement(By.cssSelector( "div:nth-child(5) > div.stats-large > div:nth-child(4) > div.value")).getText();
//	        System.out.println("Time played: " + timePlayed);
//
//	        this.TimeOnFire_Total = driver.findElement(By.cssSelector( "div:nth-child(5) > div.stats-large > div:nth-child(2) > div.value")).getText();
//	        System.out.println("Time on FIRE TOTAL: " + TimeOnFire_Total);
//	       
	        this.objectiveTime_Total = driver.findElement(By.cssSelector("div:nth-child(5) > div.stats-large > div:nth-child(3) > div.value")).getText();
	        System.out.println("objectiveTime_Total: " + objectiveTime_Total);
	        
	        this.eliminations_Most = driver.findElement(By.cssSelector("div:nth-child(8) > div.stats-large > div:nth-child(1) > div.value")).getText();
	        System.out.println("Elms MOst: " + eliminations_Most);
	        
	        this.finalBlows_Most = driver.findElement(By.cssSelector("div:nth-child(8) > div.stats-large > div:nth-child(2) > div.value")).getText();
	        System.out.println("FinalBlows MOst: " + finalBlows_Most);
	        
	        this.objectiveKills_Most = driver.findElement(By.cssSelector("div:nth-child(8) > div.stats-large > div:nth-child(3) > div.value")).getText();
	        System.out.println("Objecticve Kills MOst: " + objectiveKills_Most);
	        
	        
	        this.objectiveTime_Most = driver.findElement(By.cssSelector( "div:nth-child(8) > div.stats-large > div:nth-child(4) > div.value")).getText();
	        System.out.println("Objective Time MOst: " + objectiveTime_Most);
	        
	        
	        try {
		        this.soloKills_Most = driver.findElement(By.cssSelector("div:nth-child(8) > div.stats-large > div:nth-child(5) > div.value")).getText();
		        System.out.println("soloKills_Most: " + soloKills_Most);
		        
		        this.TimeOnFire_Most = driver.findElement(By.cssSelector("div:nth-child(6) > div.value")).getText();
		        System.out.println("TimeOnFire_Most: " + TimeOnFire_Most);
			} catch (Exception e) {
				
			}
	        
	        this.amountMedals = driver.findElement(By.cssSelector("div:nth-child(6) > div.stats-large > div:nth-child(1) > div.value")).getText();
	        System.out.println("AMount Medals: " + amountMedals);
	        
	     
	        this.goldMedal = driver.findElement(By.cssSelector("div:nth-child(6) > div.stats-large > div:nth-child(3) > div.value")).getText();
	        System.out.println("AMount Medals: " + goldMedal);
	        
	        this.silverMedal = driver.findElement(By.cssSelector("div:nth-child(6) > div.stats-large > div:nth-child(4) > div.value")).getText();
	        System.out.println("AMount Medals: " + silverMedal);
	        
	        try {
//	        	 this.bronzeMedal = driver.findElement(By.cssSelector("div:nth-child(6) > div.stats-large > div:nth-child(5) > div.value")).getText();
//	 	        System.out.println("AMount Medals: " + bronzeMedal);
	 	        
	 	       for(int i = 1; i < 8; i++)
		        {
	 	    	   System.out.println("IIIIII BETRAGR: " + i);
		        	
		        	if(driver.findElement(By.cssSelector("div:nth-child(8) > div.stats-large > div:nth-child("+ i +") > div.name")).getText().equals("Most Objective Kills")) {
		        		 this.objectiveKills_Most = driver.findElement(By.cssSelector("div:nth-child(8) > div.stats-large > div:nth-child("+ i +") > div.value")).getText();
		        		 System.out.println("ObjectiveKILLS MO=0000000000000000000000000st: " + this.objectiveKills_Most);
		        	}
		        	
		        	if(driver.findElement(By.cssSelector("div:nth-child(8) > div.stats-large > div:nth-child("+ i +") > div.name")).getText().equals("Most Objective Time")) {
		        		 this.objectiveTime_Most = driver.findElement(By.cssSelector("div:nth-child(8) > div.stats-large > div:nth-child("+ i +") > div.value")).getText();
		        		 System.out.println("ObjectiveTIME MO=0000000000000000000000000st: " + this.objectiveTime_Most);
		        	}
		        	
		        	if(driver.findElement(By.cssSelector("div:nth-child(8) > div.stats-large > div:nth-child("+ i +") > div.name")).getText().equals("Most Healing")) {
		        		 this.healing_Most = driver.findElement(By.cssSelector("div:nth-child(8) > div.stats-large > div:nth-child("+ i +") > div.value")).getText();
		        		 System.out.println("Healing MO=0000000000000000000000000st: " + this.healing_Most);
		        	}		        	
		        }
			} catch (Exception e) {
				e.printStackTrace();
			}
	        
	        try {
	        	  for (int i = 1; i < 7; i++) {

		 	    	   if(driver.findElement(By.cssSelector("div:nth-child(" + i + ") > div.name")).getText().equals("Most Solo Kills")) {
			        		 this.soloKills_Most = driver.findElement(By.cssSelector("div:nth-child(" + i + ") > div.value")).getText();
			        		 System.out.println("SoloKills MO=0000000000000000000000000st: " + this.soloKills_Most);
			        	}
			        	
			        	 if(driver.findElement(By.cssSelector("div:nth-child(" + i + ") > div.name")).getText().equals("Most Time Spent On Fire")) {
			        		 this.TimeOnFire_Most = driver.findElement(By.cssSelector("div:nth-child(" + i + ") > div.value")).getText();
			        		 System.out.println("TimeONfIREE MO=0000000000000000000000000st: " + this.TimeOnFire_Most);
			        	}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	        
	        try {
	        	for (int i = 1; i < 6; i++) {
					
		 	    	  if(driver.findElement(By.cssSelector("div:nth-child(6) > div.stats-large > div:nth-child(" + i +") > div.name")).getText().equals("Gold Medals")) {
			        		 this.goldMedal = driver.findElement(By.cssSelector("div:nth-child(6) > div.stats-large > div:nth-child(" + i +") > div.value")).getText();
			        		 System.out.println("Gold Medals " + this.goldMedal);
			        	}
			        	
			        	if(driver.findElement(By.cssSelector("div:nth-child(6) > div.stats-large > div:nth-child(" + i +") > div.name")).getText().equals("Silver Medals")) {
			        		 this.silverMedal = driver.findElement(By.cssSelector("div:nth-child(6) > div.stats-large > div:nth-child(" + i +") > div.value")).getText();
			        		 System.out.println("Silver Medals " + this.silverMedal);
			        	}
			        	
			        	if(driver.findElement(By.cssSelector("div:nth-child(6) > div.stats-large > div:nth-child(" + i +") > div.name")).getText().equals("Bronze Medals")) {
			        		 this.bronzeMedal = driver.findElement(By.cssSelector("div:nth-child(6) > div.stats-large > div:nth-child(" + i +") > div.value")).getText();
			        		 System.out.println("Bronze Medals " + this.bronzeMedal);
			        	}
	        	}
			} catch (Exception e) {
				e.printStackTrace();
			}
	       
	       
    	try {
    		
    		for(int i = 1; i < 5; i++)
    		{

	        	if(driver.findElement(By.cssSelector("div:nth-child(5) > div.stats-large > div:nth-child(" + i +") > div.name")).getText().equals("Objective Time")) {
	        		 this.objectiveTime_Total = driver.findElement(By.cssSelector("div:nth-child(5) > div.stats-large > div:nth-child(" + i +") > div.value")).getText();
	        		 System.out.println("Objective Time " + this.objectiveTime_Total);
	        	}
	        	
	        	if(driver.findElement(By.cssSelector("div:nth-child(5) > div.stats-large > div:nth-child(" + i +") > div.name")).getText().equals("Time Played")) {
	        		 this.timePlayed = driver.findElement(By.cssSelector("div:nth-child(5) > div.stats-large > div:nth-child(" + i +") > div.value")).getText();
	        		 System.out.println("timePlayed " + this.timePlayed);
	        	}
	        	
	        	  if(driver.findElement(By.cssSelector("div:nth-child(5) > div.stats-large > div:nth-child(" + i +") > div.name")).getText().equals("Time Spent On Fire")) {
		        		 this.TimeOnFire_Total = driver.findElement(By.cssSelector("div:nth-child(5) > div.stats-large > div:nth-child(" + i +") > div.value")).getText();
		        		 System.out.println("TimeOnFire_Total " + this.TimeOnFire_Total);
		        	}
	        	
    		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	      
	       
	        
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
     * Searches for competitive stats for the specific hero on overwatchtracker.com
     * 
     *  @param driver
     *         The selenium webdriver needed for scraping
     *         
     * 	@param hero
     *         The specific hero you want the statistics from
     *        
     *	@exception catches ElementNotFoundException
     *
     */
	public void searchCompetitiveStatsHero(WebDriver driver, String hero) {
		
		try {
			searchQuickPlayStatsHero(driver, hero);
			
			this.winRate = driver.findElement(By.cssSelector("div:nth-child(6) > div.stats-large > div:nth-child(4) > div.value")).getText();
	        System.out.println( "winRate Total: " + winRate);
	        
	        this.eliminations_Average = driver.findElement(By.cssSelector("div:nth-child(2) > div.stats-large > div:nth-child(4) > div.value")).getText();
	        System.out.println("eliminations_Average: " + eliminations_Average);
	        
	        this.gamesPlayed = driver.findElement(By.cssSelector("div:nth-child(5) > div.stats-large > div:nth-child(2) > div.value")).getText();
	        System.out.println("gamesPlayed: " + gamesPlayed);
	        
	        this.TimeOnFire_Total = driver.findElement(By.cssSelector("div:nth-child(5) > div.stats-large > div:nth-child(3) > div.value")).getText();
	        System.out.println("Time on FIRE TOTAL: " + TimeOnFire_Total);
	        
	        this.objectiveTime_Total = driver.findElement(By.cssSelector("div:nth-child(5) > div.stats-large > div:nth-child(4) > div.value")).getText();
	        System.out.println("objectiveTime_Total: " + objectiveTime_Total);
	        
	        this.timePlayed = driver.findElement(By.cssSelector("div:nth-child(5) > div.stats-large > div:nth-child(5) > div.value")).getText();
	        System.out.println("timePlayed: " + timePlayed);
	        
	        this.goldMedal = driver.findElement(By.cssSelector("div:nth-child(6) > div.stats-large > div:nth-child(2) > div.value")).getText();
	        System.out.println("AMount Medals: " + goldMedal);
	        
	        this.silverMedal = driver.findElement(By.cssSelector("div:nth-child(6) > div.stats-large > div:nth-child(3) > div.value")).getText();
	        System.out.println("AMount Medals: " + silverMedal);
	        
	        
	        this.bronzeMedal = driver.findElement(By.cssSelector("div:nth-child(6) > div.stats-large > div:nth-child(4) > div.value")).getText();
	        System.out.println("AMount Medals: " + bronzeMedal);
	        
	        
	   
	        for(int i = 0; i < 10; i++)
	        {
	        	if(driver.findElement(By.cssSelector("div:nth-child(8) > div.stats-large > div:nth-child("+ i +") > div.name")).getText().equals("Most Healing"))
	        	{
	        		 this.healing_Most = driver.findElement(By.cssSelector("div:nth-child(8) > div.stats-large > div:nth-child("+ i +") > div.name")).getText();
	        		 System.out.println("Healing MOst: " + this.healing_Most);
	        	}
	        }
	       
		} catch (Exception e) {
		}
		
		
		
        
	}
	
	
	
	
	/**
     * The current competitive skill rating
     *
     * @return The current competitive skill rating
     */
	public String getSkillRating() {
		return skillRating;
	}


	/**
     * The current soloKills 
     * If it has now value the solo kills will be 0
     *
     * @return The current solo kills as a String
     */
	public String getSoloKillsTotal() {
		
		try {
			
			String str = "";
			for(int i = 0; i < soloKills_Total.length(); i++)
			{
				String inputStr = soloKills_Total.substring(i, i+1);
				
				if(inputStr.equals(","))
				{
					str += "";
				}else
				{
					str+= inputStr;
				}
				
				
			}
			
			return str;	
			
		} catch (NullPointerException e) {

			return "0";
		}
		

		}
	/**
     * The current final Blows 
     * If it has now value the it will be 0
     *
     * @return The current final Blows as an Integer
     */
	public String getFinalBlowsTotal() {
		
		try {
			String str = "";

			for(int i = 0; i < finalBlows_Total.length(); i++)
			{
				String inputStr = finalBlows_Total.substring(i, i+1);
				
				if(inputStr.equals(","))
				{
					str += "";
				}else
				{
					str+= inputStr;
				}
				
				
			}
			
			return str;
		} catch (NullPointerException e) {

			return "0";
		}
		
		
	}
	
	/**
     * The current objective kills
     * If it has now value the it will be 0
     *
     * @return The current objective kills as an Integer
     */
	public int getObjectiveKillsTotal() {
		try {
			return Integer.parseInt(objectiveKills_Total);

		} catch (NullPointerException | NumberFormatException e) {
			return 0;
		
		}
	
	}
	
	/**
     * The current objective time
     * If it has now value the it will be 0
     *
     * @return The current objective time as an Integer
     */
	public int getObjectiveTimeTotal() {
		
	
		try
		{
			System.out.println(objectiveTime_Total);
			String array[] = objectiveTime_Total.split("m");
			String min = array[0];
		
			String array2[] = array[1].trim().split("s");
			String sec = array2[0];

			
			int out = (Integer.parseInt(min) * 60) + Integer.parseInt(sec);
			
			return out;
		}catch (Exception e) {
			
			try {
				e.printStackTrace();
				String array[] = objectiveTime_Total.split("h");
				String hours = array[0];
			
				String array2[] = array[1].trim().split("m");
				String min = array2[0];

				
				int out = (Integer.parseInt(hours) * 3600) + (Integer.parseInt(min) * 60 );
				
				return out;
			} catch (NullPointerException e2) {

				return 0;
			}
		}
		
	}

	/**
     * The current time spent on fire
     * If it has now value the it will be 0
     *
     * @return The current objective time as an Integer
     */
	public int getTimeOnFireTotal() {
		
		try
		{
			String array[] = TimeOnFire_Total.split("m");
			String min = array[0];
		
			String array2[] = array[1].trim().split("s");
			String sec = array2[0];

			
			int out = (Integer.parseInt(min) * 60) + Integer.parseInt(sec);
			System.out.println("OIUR: " + out);
			
			return out;
		}catch (Exception e) {
			try {
				String array[] = TimeOnFire_Total.split("h");
				String hours = array[0];
			
				String array2[] = array[1].trim().split("m");
				String min = array2[0];

				
				int out = (Integer.parseInt(hours) * 3600) + (Integer.parseInt(min) * 60 );
				
				return out;
			} catch (Exception e2) {
				
				
				try {
					String array[] = TimeOnFire_Total.split("s");
					String sec = array[0];
				
				

					
					int out = (Integer.parseInt(sec));
					
					return out;

				} catch (NullPointerException e3) {

					return 0;
				}				
			}
		}
	}
	
	public String getWinRate() {
		return winRate;
	}

	/**
     * The playing time of the User
     * Only Quickplay & Competitive
     *
     * @return The current play time
     */
	public String getTimePlayed() {
		return timePlayed;
	}

	/**
     * The profile picture User
     *
     * @return The current profile Image
     */
	public String getUserImage() {
		return userImage;
	}
	/**
     * The current kills deaths ratio (K/D)
     *
     * @return The current kills deaths ratio 
     */
	public String getEliminationsKD() {
		return eliminations_KD;
	}

	/**
     * The current kills assist deaths ratio (KA/D)
     *
     * @return The current kills assist deaths ratio 
     */
	public String getEliminationsKAD() {
		return eliminations_kad;
	}
	
	/**
     * All eliminations divided by the number of games
     *
     * @return The eliminations average per Game
     */
	public String getEliminationsAverage() {
		return (eliminations_Average);
	}
	
	public String getDamageAverage() {
		return damage_Average;
	}

	public String getHealingAverage() {
		return healing_Average;
	}


	public String getEliminationsTotal() {
		try {
			
			if(eliminations_Total.length() < 1)
				return "0";
			else
				return eliminations_Total;
		} catch (NullPointerException e) {
			
			return "0";
		}
		
	}

	public String getDamageTotal() {
		return damage_Total;
	}



	public String getEnviromentalKillsTotal() {
		return enviromentalKills_Total;
	}

	public String getGamesWon() {
		return gamesWon;
	}

	public String getGamesPlayed() {
		try
		{
			if(Integer.parseInt(gamesPlayed) == 0)
			{
				return "1";
			}
		}catch(Exception exception)
		{
			return "1";  //TODO: Implement real games statisrik
		}
	
		return gamesPlayed;
	}

	public String getHealingTotal() {
		return healing_Total;
	}

	public String getEliminationsMost() {
		return eliminations_Most;
	}

	public String getFinalBlowsMost() {
		return finalBlows_Most;
	}

	public String getDamageMost() {
		return damage_Most;
	}

	public String getHealingMost() {
		return healing_Most;
	}

	public String getObjectiveKillsMost() {
		return objectiveKills_Most;
	}

	public String getObjectiveTimeMost() {
		return objectiveTime_Most;
	}

	public String getSoloKillsMost() {
		return soloKills_Most;
	}

	public String getTimeOnFireMost() {
		return TimeOnFire_Most;
	}

	public String getName() {
		return name;
	}

	public String getLvl() {
		return lvl;
	}

	public String getFavHero() {
		return favHero;
	}

	public String getEnviromentalDeathsTotal() {
		return null;
	}

	public String getMode() {
		
		if(competitive) 
			return "Competitive";
		else
			return "Quick Play";
		
	}
	
	public String getHeroImage() {
		return heroImage;
	}



	public String getMultiKillsTotal() {
		return multiKills_Total;
	}



	public String getDeathsTotal() {
		return deaths_Total;
	}



	public String getDeathsAverage() {
		return deaths_Average;
	}



	public boolean isCompetitive() {
		return competitive;
	}


	/**
     * All bronze medals
     *
     * @return the bronze medals the player got
     */
	public String getBronzeMedals() {
		return bronzeMedal;
	}



	/**
     * All silver medals
     *
     * @return the silver medals the player got
     */
	public String getSilverMedals() {
		return silverMedal;
	}



	/**
     * All gold medals
     *
     * @return the gold medals the player got
     */
	public String getGoldMedals() {
		return goldMedal;
	}


	public String getAmountMedals() {
		return amountMedals;
	}

}
	
	

