/**
 * @(#)killable.java
 *
 *
 * @author 
 * @version 1.00 2020/3/25
 */


public interface killable 
{
	public boolean isDead();
	
	public void die();
	
	public int getCurrentHP();
    
    public int getTotalHP();
    
    public void takeDamage(int damage);
}