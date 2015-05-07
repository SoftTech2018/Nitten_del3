package dtu.server.dal;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dtu.client.service.KartotekService;
import dtu.shared.IngrediensDTO;
import dtu.shared.OperatoerDTO;
import dtu.shared.ProdBatchInfo;
import dtu.shared.ReceptViewDTO;

public class PersonDAOtransient extends RemoteServiceServlet implements KartotekService  {

	private List<OperatoerDTO> oprList;
	private List<ReceptViewDTO> receptViewList;
	private List<ProdBatchInfo> prodList;
	private int oprID;

	public PersonDAOtransient() throws Exception {
		oprList = new ArrayList<OperatoerDTO>();
		receptViewList = new ArrayList<ReceptViewDTO>();
		prodList = new ArrayList<ProdBatchInfo>();
		oprID = 1;
		
		//Create test data
		this.setup();
	}
	
	private void setup() throws Exception{
		this.savePerson(new OperatoerDTO(-1, "Hans", "HA", "123456-7890", "02324it!", true, false, false));
		this.savePerson(new OperatoerDTO(-1, "Grete", "GR", "987654-3210", "02324it!", false, true, false));
		this.savePerson(new OperatoerDTO(-1, "Grimm", "GI", "025814-9637", "02324it!", false, false, true));
		
		List<IngrediensDTO> ingr = new ArrayList<IngrediensDTO>();
		ingr.add(new IngrediensDTO("Humle", 0.8));
		ingr.add(new IngrediensDTO("Vand", 0.8));
		ingr.add(new IngrediensDTO("Flaske", 1));
		receptViewList.add(new ReceptViewDTO(1, "Øl", ingr));
		
		List<IngrediensDTO> ingr2 = new ArrayList<IngrediensDTO>();
		ingr2.add(new IngrediensDTO("Dej", 1));
		ingr2.add(new IngrediensDTO("Tomat", 5.4));
		ingr2.add(new IngrediensDTO("Ost", 8.7));
		ingr2.add(new IngrediensDTO("Skinke", 2.5));
		receptViewList.add(new ReceptViewDTO(2, "Pizza", ingr2));
		
		List<IngrediensDTO> ingr3 = new ArrayList<IngrediensDTO>();
		ingr3.add(new IngrediensDTO("Havsalt", 88));
		ingr3.add(new IngrediensDTO("Kartoffel", 0.4));
		ingr3.add(new IngrediensDTO("Olie", 5.7));
		receptViewList.add(new ReceptViewDTO(3, "Chips", ingr3));
		
		prodList.add(new ProdBatchInfo(1, "Øl", 96, 1, 2, this.getOperatoer(2).getNavn(), 2));
		prodList.add(new ProdBatchInfo(2, "Pizza", 5, 2, 2, this.getOperatoer(2).getNavn(), 2));
		prodList.add(new ProdBatchInfo(3, "Chips", 10, 3, 2, this.getOperatoer(2).getNavn(), 2));
	}

	@Override
	public void savePerson(OperatoerDTO p) throws Exception {
		p.setOprId(oprID++);
		oprList.add(p);
	}

	@Override
	public void updatePerson(OperatoerDTO p) throws Exception {
		for (OperatoerDTO per : oprList){
			if (per.getOprId() == p.getOprId()){
				per.setAdmin(p.isAdmin());
				per.setCpr(p.getCpr());
				per.setFarmaceut(p.isFarmaceut());
				per.setIni(p.getIni());
				per.setNavn(p.getNavn());
				per.setOperatoer(p.isOperatoer());
				per.setPassword(p.getPassword());
				break;
			}
		}
	}

	@Override
	public List<OperatoerDTO> getPersons() throws Exception {
		return oprList;
	} 

	@Override
	public List<OperatoerDTO> getOprView() throws Exception {
		List<OperatoerDTO> output = new ArrayList<OperatoerDTO>();
		for (OperatoerDTO p : oprList){
			if (p.isAdmin() || p.isFarmaceut() || p.isOperatoer()){
				output.add(p);
			}
		}
		return output;
	} 

	@Override
	public int getSize() throws Exception {
		return oprList.size();
	}

	@Override
	public void deletePerson(int id) throws Exception {
		for (int i=0; i< oprList.size(); i++){
			if (oprList.get(i).getOprId() == id){
				oprList.get(i).setAdmin(false);
				oprList.get(i).setFarmaceut(false);
				oprList.get(i).setOperatoer(false);
				break;
			}
		}
	}

	@Override
	public List<ReceptViewDTO> getReceptView() throws Exception {
		return receptViewList;
	}

	@Override
	public OperatoerDTO getOperatoer(int id) throws Exception {
		for (OperatoerDTO opr : oprList){
			if (opr.getOprId() == id){
				return opr;
			}
		}
		throw new Exception("Operatør kunne ikke findes.");
	}

	@Override
	public List<ProdBatchInfo> getProdBatchInfoView() throws Exception {
		return prodList;
	}
}
