package br.com.tcc.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="SelectOneMenuConverter")
public class SelectOneMenuConverter implements Converter {

	@SuppressWarnings("unchecked")
	public Object getAsObject(FacesContext facesContext, UIComponent uicomp, String value) {
		List<Menuable> list = new ArrayList<Menuable>();
		List<UIComponent> uicompList = uicomp.getChildren();
		for (UIComponent comp : uicompList) {
			if (comp instanceof UISelectItems) {
				list.addAll((List<Menuable>) ((UISelectItems) comp).getValue());
			}
		}
		
		try {
			
			try {
				Integer.valueOf(value);

				Map<Integer, Menuable> items = new HashMap<Integer, Menuable>();
				
				for (Menuable m : list) {
					items.put(Integer.valueOf(m.getIdentifier().toString()), m);
				}
				
				return "-1".equals(value) ? null : items.get(Integer.valueOf(value));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Map<Object, Menuable> items = new HashMap<Object, Menuable>();
			
			for (Menuable m : list) {
				items.put(m.getIdentifier(), m);
			}
			
			return "-1".equals(value) ? null : items.get(value);
		} catch (Exception e) {
			return null;
		}
	}

	public String getAsString(FacesContext facesContext, UIComponent uicomp,
			Object entity) {
		 
		return entity == null || "-1".equals(entity) || "".equals(entity) ? "-1" : String.valueOf(((Menuable) entity).getIdentifier());
	}

}
