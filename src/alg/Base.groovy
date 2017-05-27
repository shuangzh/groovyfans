package alg

/**
 * Created by admin on 2017/5/26.
 */
class Base {

    static compose(list, count) {
        def retlist = []
        if (count == 1) {
            list.each { retlist <<  [it] }
        } else if(count == list.size){
            retlist << list
        }else {
            def e0 = list[0]
            def newlist = list[1..(list.size - 1)]
            def part0 = compose(newlist, count - 1)
            def part1 = compose(newlist, count)
            part0.each { retlist << (it << e0) }
            retlist.addAll(part1)
        }
        return retlist
    }

    static  arrange(list, count){
        def retlist = []
        if(count == 1){
            list.each {retlist << [it]}
        }else {
            for(int i=0; i<list.size; i++){
                def e =list[i]
                def nlist = list[0..<list.size]
                nlist.remove(i)
                def part=arrange(nlist, count-1)
                part.each {
                    def elm=[e]
                    elm.addAll(it)
                    retlist << elm
                }
            }
        }
        return  retlist
    }

    static arragerep(list, count){
        def retlist=[]
        if(count ==1 ){
            list.each { retlist << [it]}
        }else {
            for(int i=0; i<list.size; i++){
                def part = arragerep(list, count-1)
                part.each {
                    def elem=[list[i]]
                    elem.addAll(it)
                    retlist << elem
                }
            }
        }
        retlist
    }

    static main(args){
        println compose([1,2,3,4], 3)
        println arrange([1,2,3,4], 2)
        println arragerep([1,2,3],2)
    }
}

