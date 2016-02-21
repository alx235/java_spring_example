package mvc.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mvc.model.AbsDao;
import mvc.model.DefaultDao;
import mvc.model.DefaultDaoImpl;
import mvc.model.Disk;
import mvc.model.DiskDao;
import mvc.model.DiskDaoImpl;
import mvc.model.TakenItem;
import mvc.model.TakenItemDao;
import mvc.model.TakenItemDaoImpl;
import mvc.model.User;
import mvc.model.UserDao;
import mvc.model.UserDaoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@EnableWebMvc
@Controller
public class DataManage {
		
@Autowired
private UserDao userDao;

@Autowired
private DefaultDao defaultDao;

@Autowired	
private TakenItemDao takenItemDao;

@Autowired	
private DiskDao diskDao;
	

	@RequestMapping(value = "/", method=RequestMethod.GET)
	public String GoToAuth() throws SQLException, FileNotFoundException, IOException
	{
		return "redirect:/auth";
	}

	@RequestMapping(value = "/auth", method=RequestMethod.POST)
	public String AUTHLoadPost(Model model,HttpSession session,HttpServletRequest request,
			RedirectAttributes redirectAttrs) throws FileNotFoundException, IOException
	{
		String Username = request.getParameter("UserName");
		String pass = request.getParameter("password");
		
		if (Username.length()>0 && pass.length()>0)
		{
			if (userDao.check_account(Username))
			{
				User user = new User();
				user.Setpassword(pass);
				user.SetUserName(Username);
				AbsDao.class.cast(userDao).store(user);
				
				SetAccess(session,Username,user.GetId());

				model.addAttribute("Access", "1");
				model.addAttribute("NewUser", "1");
				return "redirect:/main";
			}	
			else
			{
				Long Userid= userDao.check_pass(Username,pass); 
				if (Userid>-1)
				{
					SetAccess(session,Username,Userid);
					model.addAttribute("Access", "1");
					return "redirect:/main";
				}
				else
				{
					model.addAttribute("error", "Access Failed");
				}
			}
		}
		else
		{
			model.addAttribute("error", "Length of login/pass must be more than 0");
		}
		return "auth";
	}
	
	@RequestMapping(value = "/main", method=RequestMethod.GET)
	public String GoToMain() throws SQLException, FileNotFoundException, IOException
	{
		return "main";
	}
	
	@RequestMapping(value = "/auth", method=RequestMethod.GET)
	public String FirstLoad(Model model,HttpSession session) throws SQLException, FileNotFoundException, IOException
	{
		if (GetAccess(session))
		{
			model.addAttribute("Access", "1");
		}
		else
		{	
			switch (defaultDao.CheckBase()){
				case 0: 
					model.addAttribute("errorDB", "Not connected: " + defaultDao.get_error() );
					break;
				case 2: 
					
					CreateDefVal("Spring","1","1");
					
					CreateDefVal("Spring2","2","2");
					
					break;
				}
		}
		return "auth";
	}
	
	@RequestMapping(value = "/logout", method=RequestMethod.POST)
	public String Logout(HttpSession session,RedirectAttributes redirectAttrs)
	{
		LockAccess(session);
		redirectAttrs.addFlashAttribute("LogOut", "1");
		return "redirect:/auth";
	}
	
	private void LockAccess(HttpSession session)
	{	
		session.removeAttribute("user");
		session.removeAttribute("access");
		session.removeAttribute("userId");
	}
	
	private void SetAccess(HttpSession session,String userName, Long userid)
	{	
		session.setAttribute("access", "true");
		session.setAttribute("user", userName);
		session.setAttribute("userId", userid);
	}
	
	private Long getUserId(HttpSession session)
	{	
		Long id = (Long)session.getAttribute("userId");
		if (id==null)
		{
			return (long) -1;
		}
		else
			return id;
	}
	
	private String getUserName(HttpSession session)
	{	
		String name = (String)session.getAttribute("user");
		if (name==null)
		{
			return "";
		}
		else
			return name;
	}
	
	private boolean GetAccess(HttpSession session)
	{
		String access = (String)session.getAttribute("access");
		if (access==null)
		{
			return false;
		}
		else
			return access.equals("true");
		
	}
	
	@RequestMapping(value = "/newdisk", method=RequestMethod.GET)
	public String NewdiskLoad(Model model,HttpSession session)
	{

		return "newdisk";
	}
	
	@RequestMapping(value = "/newdisk", method=RequestMethod.POST)
	public String NewdiskLoadPot(Model model,HttpSession session,
			HttpServletRequest request) throws FileNotFoundException, IOException
	{
		String diskname = request.getParameter("Disk name:");
		Disk disk = new Disk();
		disk.SetDisk(diskname);
		AbsDao.class.cast(diskDao).store(disk);
		return "redirect:/newdisk";
	}
	
	@RequestMapping(value = "/freelist", method=RequestMethod.GET)
	public String FreelistLoad(Model model,HttpSession session) throws FileNotFoundException, IOException
	{
		List<Object[]> result = null;
		int size = -1; 
		result  = takenItemDao.find_free(getUserId(session));
		if (result!=null)
			size = result.size();
		if (size>0)
		{
			String matrix [] = new String [size]; 
			BigInteger matrix2 [] = new BigInteger [size];
			int i=0;
			for (Object[] resultElement : result) 
			{
				matrix[i]=(String)resultElement[0];
				matrix2[i]=(BigInteger) resultElement[1];
				i++;
		}
		model.addAttribute("currents", matrix);
		model.addAttribute("currents2", matrix2);
		}
		return "freelist";
	}
	
	@RequestMapping(value = "/freelist", method=RequestMethod.POST)
	public String FreelistLoadPost(Model model,HttpSession session,
			HttpServletRequest request) throws FileNotFoundException, IOException
	{
		String [] val = request.getParameterValues("check");
		
		if (val!=null)
		{
			int n = val.length;
			long index=-1;
			if (val!=null)
			{
				for (int i=0;i<n;i++)
				{	
					index = Integer.parseInt(val[i]);
					
					CreateValTake(index,getUserId(session));
				}
			}
		}
				
		return "redirect:/freelist";
	}
	
	@RequestMapping(value = "/recieved", method=RequestMethod.GET)
	public String RecievedLoad(Model model,HttpSession session) throws FileNotFoundException, IOException
	{
		List<Object[]> result3 = null;
		int size = -1;
		result3  = takenItemDao.find_Received(getUserId(session));
		if (result3!=null)
			size = result3.size();
		if (size>0)
		{
		String matrix [] = new String [size]; 
		String matrix1 [] = new String [size]; 
		BigInteger matrix2 [] = new BigInteger [size];
		int i=0;
		for (Object[] resultElement : result3) 
		{
			matrix[i]=(String)resultElement[0];
			matrix1[i]=(String)resultElement[1];
			matrix2[i]=(BigInteger)resultElement[2];
			i++;
		}
		model.addAttribute("currents", matrix);
		model.addAttribute("currents2", matrix2);
		model.addAttribute("currents1", matrix1);
		}
		return "recieved";
	}
	
	@RequestMapping(value = "/recieved", method=RequestMethod.POST)
	public String recievedLoadPost(Model model,HttpSession session,
			HttpServletRequest request) throws FileNotFoundException, IOException
	{
		String [] val = request.getParameterValues("check");
		
		if (val!=null)
		{
			int n = val.length;
			Long index=(long)-1;
			if (val!=null)
			{
				for (int i=0;i<n;i++)
				{	
					index = (long)Integer.parseInt(val[i]);
					takenItemDao.backDisk(getUserId(session), index);
				}
			}
		}	
		return "redirect:/recieved";
	}
	
	@RequestMapping(value = "/taken", method=RequestMethod.GET)
	public String TakenlistLoad(Model model,HttpSession session) throws FileNotFoundException, IOException
	{
		List<Object[]> result2 = null;
		int size  =-1;
		result2  = takenItemDao.find_Taken(getUserId(session));
		if (result2!=null)
			size = result2.size();
		if (size>0)
		{
			String matrix [] = new String [size];
			int i=0;
			for (Object[] resultElement : result2) 
			{
				matrix[i]=(String)resultElement[0];
				i++;
			}
			model.addAttribute("currents", matrix);
		}
		return "taken";
	}
	
	private void CreateDefVal(String diskname,String usr_name,String pass) throws FileNotFoundException, IOException
	{
		
		Disk disk = new Disk();
		disk.SetDisk(diskname);
		
		User user = new User();
		user.Setpassword(pass);
		user.SetUserName(usr_name);
		AbsDao.class.cast(userDao).store(user);
		
		disk.SetUserId(user.GetId());
		AbsDao.class.cast(diskDao).store(disk);
	}
	
	private void CreateValTake(Long disk_id,Long UserId) throws FileNotFoundException, IOException
	{	
		TakenItem takenItem = new TakenItem();
		takenItem.setDiskId(disk_id);
		takenItem.setUserId(UserId);
		AbsDao.class.cast(takenItemDao).store(takenItem);
	}

}
