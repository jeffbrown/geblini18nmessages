package geblini18nmessages

import grails.gorm.services.Service

@Service(PersonData)
interface PersonDataService {

    PersonData get(Serializable id)

    List<PersonData> list(Map args)

    Long count()

    void delete(Serializable id)

    PersonData save(PersonData personData)

}