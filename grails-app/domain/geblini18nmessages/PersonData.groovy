package geblini18nmessages

class PersonData {
    String name
    String eyeColor
    static constraints = {
        eyeColor validator: { val ->
            // valid
            if(val in ['blue', 'green', 'brown']) return true

            // invalid
            return ['personData.eye.color.invalid', val]
        }
    }
}
