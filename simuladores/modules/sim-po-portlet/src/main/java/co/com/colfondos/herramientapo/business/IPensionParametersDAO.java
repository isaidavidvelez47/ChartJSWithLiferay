package co.com.colfondos.herramientapo.business;

import co.com.colfondos.herramientapo.model.PensionFund;
import co.com.colfondos.herramientapo.model.Person;
import co.com.colfondos.tech.ws.cliente.funcionario.service_funcionario.RentaPortafolioVO;

import java.util.List;
import java.util.Map;

/**
 * Interface that provides methods to:
 * <ul>
 *     <li>Establish simulator parameters</li>
 *     <li>Read parameters from WS</li>
 *     <li>Make simulator calculations</li>
 * </ul>
 *
 * @author Pragma S.A. - Guillermo Grajales
 * @version 1.0
 */
public interface IPensionParametersDAO {

    void establishParameters(Person person, String projectionType);

    boolean hasRentabilityValues();

    boolean readPensionParameters(int defaultMaleAge, int defaultFemaleAge, double defaultMinSalary);

    void setRentabilityValues(List<RentaPortafolioVO> rentaPortafolioVOList);

    Map<String, List<Object>> calculateResultValues(PensionFund pensionFund, Person person);
}
