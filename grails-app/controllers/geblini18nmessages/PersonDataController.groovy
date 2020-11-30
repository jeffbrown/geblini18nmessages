package geblini18nmessages

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PersonDataController {

    PersonDataService personDataService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond personDataService.list(params), model:[personDataCount: personDataService.count()]
    }

    def show(Long id) {
        respond personDataService.get(id)
    }

    def create() {
        respond new PersonData(params)
    }

    def save(PersonData personData) {
        if (personData == null) {
            notFound()
            return
        }

        try {
            personDataService.save(personData)
        } catch (ValidationException e) {
            respond personData.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'personData.label', default: 'PersonData'), personData.id])
                redirect personData
            }
            '*' { respond personData, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond personDataService.get(id)
    }

    def update(PersonData personData) {
        if (personData == null) {
            notFound()
            return
        }

        try {
            personDataService.save(personData)
        } catch (ValidationException e) {
            respond personData.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'personData.label', default: 'PersonData'), personData.id])
                redirect personData
            }
            '*'{ respond personData, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        personDataService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'personData.label', default: 'PersonData'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'personData.label', default: 'PersonData'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
